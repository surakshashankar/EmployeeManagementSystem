package com.sample.employmanagementsystem.util;

import com.sample.employmanagementsystem.ServiceImpl.CustomUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.security.sasl.AuthenticationException;
import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

  @Autowired
  CustomUserDetailsService userDetailsService;

  @Autowired
  JwtUtil jwtUtil;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

    final String header = request.getHeader("Authorization");
    String username= null;
    String jwt = null;

    try {
      String path = request.getRequestURI();
      if ("/api/login".equals(path)) {
        filterChain.doFilter(request, response); // Skip JWT processing
        return;
      }

        if (header != null && header.startsWith("Bearer ")) {
          jwt = header.substring(7);

          try {

            username = jwtUtil.getUsername(jwt);
            if (username != null) {
              UserDetails userDetails = userDetailsService.loadUserByUsername(username);

              if (jwtUtil.validateToken(userDetails.getUsername(), jwt)) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                        = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
              }
            }
          } catch (Exception ex) {
            throw new RuntimeException(ex);
          }

        } else {
          System.out.println("Please provide valid authorization");
          throw new AuthenticationException("Employee not authorised");
        }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

    filterChain.doFilter(request, response);
  }
}
