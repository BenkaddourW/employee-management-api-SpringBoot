package com.example.demonstration.controllers;


import com.example.demonstration.abstracts.DepartmentService;
import com.example.demonstration.dtos.DepartmentCreate;
import com.example.demonstration.entities.Department;
import com.example.demonstration.shared.GlobalResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping
    public ResponseEntity<GlobalResponse<List<Department>>> findAll() {
        List<Department> departments = departmentService.findAll();

        return new ResponseEntity<>(new GlobalResponse<>(departments), HttpStatus.OK);
    }

    @GetMapping("/{departmentId}")
    public ResponseEntity<GlobalResponse<Department>> findOne(@PathVariable("departmentId") UUID departmentId) {

        Department department = departmentService.findOne(departmentId);

        return new ResponseEntity<>(new GlobalResponse<>(department), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<GlobalResponse<Department>> createDepartment(@RequestBody @Valid DepartmentCreate departmentCreate) {
        Department department = departmentService.createDepartment(departmentCreate);
        return new ResponseEntity<>(new GlobalResponse<>(department), HttpStatus.OK);
    }

    @DeleteMapping("/{departmentId}")
    public ResponseEntity<GlobalResponse<Department>> deleteDepartment(@PathVariable("departmentId") UUID departmentId) {
        departmentService.deleteDepartment(departmentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}
