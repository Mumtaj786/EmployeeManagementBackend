package com.example.employee_management_system.repository;

import com.example.employee_management_system.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
