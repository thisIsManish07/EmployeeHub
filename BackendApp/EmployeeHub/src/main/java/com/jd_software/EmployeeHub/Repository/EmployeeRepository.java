package com.jd_software.EmployeeHub.Repository;

import com.jd_software.EmployeeHub.Entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EmployeeRepository extends JpaRepository<Employee, UUID> {
    Page<Employee> findByNameContainingIgnoreCaseOrDepartmentContainingIgnoreCaseOrStatusContainingIgnoreCase(
            String name, String department, String status, Pageable pageable);
}
