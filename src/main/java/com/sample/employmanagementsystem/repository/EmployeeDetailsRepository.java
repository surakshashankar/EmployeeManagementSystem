package com.sample.employmanagementsystem.repository;

import com.sample.employmanagementsystem.entity.EmployeeDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeDetailsRepository extends JpaRepository<EmployeeDetails, Integer>
{

    List<EmployeeDetails> findByRoleId(Integer roleId);

    EmployeeDetails findByUserName(String username);
}
