package com.sample.employmanagementsystem.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtil {

  private final static String secretKey = "this_is_my_secret_key_which_is_private";

  public String generateToken(String username) {
    return Jwts.builder()
            .subject(username)
            .issuedAt(new Date())
            .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 *10))
            .signWith(getSecretKey(), SignatureAlgorithm.HS256)
            .compact();
  }

  public static SecretKey getSecretKey() {
    byte [] key = secretKey.getBytes(StandardCharsets.UTF_8);
    return Keys.hmacShaKeyFor(key);
  }

  public Claims extractPayload(String token) {
    return Jwts.parser()
            .verifyWith(getSecretKey()).build()
            .parseClaimsJws(token)
            .getPayload();
  }

  public String getUsername(String jwt) {
    return extractPayload(jwt).getSubject();
  }

  public boolean validateToken(String username, String jwt) {
    String user = getUsername(jwt);
    return user.equals(username) && !isTokenExpired(jwt);
  }

  public boolean isTokenExpired(String jwt) {
    return extractPayload(jwt).getExpiration().before(new Date());
  }
}
