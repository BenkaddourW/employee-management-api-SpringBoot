package com.example.demonstration.abstracts;

import com.example.demonstration.dtos.DepartmentCreate;
import com.example.demonstration.entities.Department;

import java.util.List;
import java.util.UUID;

public interface DepartmentService {


    Department findOne(UUID departmentId);

    List<Department> findAll();

    Department createDepartment(DepartmentCreate departmentCreate);

    //    Department updateDepartment(UUID departmentId, Department department);
    void deleteDepartment(UUID departmentId);
}
