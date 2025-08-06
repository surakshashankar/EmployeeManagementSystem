package com.sample.employmanagementsystem.repository;

import com.sample.employmanagementsystem.entity.Departments;
import com.sample.employmanagementsystem.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentsRepository extends JpaRepository<Departments, Integer> {

}


