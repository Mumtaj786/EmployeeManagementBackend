package com.example.employee_management_system.controller;


import com.example.employee_management_system.model.Department;
import com.example.employee_management_system.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;


    @GetMapping
    public List<Department> getAll() {
        return departmentService.getAllDepartments();
    }

    @PostMapping
    public Department create(@RequestBody Department d) {
        return departmentService.createDepartment(d);
    }
}
