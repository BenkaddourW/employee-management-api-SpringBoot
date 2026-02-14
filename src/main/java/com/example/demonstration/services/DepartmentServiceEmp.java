package com.example.demonstration.services;

import com.example.demonstration.abstracts.DepartmentService;
import com.example.demonstration.dtos.DepartmentCreate;
import com.example.demonstration.entities.Department;
import com.example.demonstration.repositories.DepartmenRepo;
import com.example.demonstration.shared.CustomResponseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DepartmentServiceEmp implements DepartmentService {
    @Autowired
    private DepartmenRepo departmentRepo;


    @Override
    public Department findOne(UUID departmentId) {
        Department department = departmentRepo.findById(departmentId)
                .orElseThrow(() -> CustomResponseException.ResourceNotFound(
                        "department with ID " + departmentId + " not found"
                ));
        return department;

    }

    @Override
    public List<Department> findAll() {
        return departmentRepo.findAll();
    }

    @Override
    public void deleteDepartment(UUID departmentId) {
        Department department = departmentRepo.findById(departmentId)
                .orElseThrow(() -> CustomResponseException.ResourceNotFound(
                        "Employee with ID " + departmentId + " not found"
                ));
        departmentRepo.deleteById(departmentId);

    }


    @Override
    public Department createDepartment(DepartmentCreate departmentcreate) {
        Department newDepartment = new Department();
        newDepartment.setName(departmentcreate.name());

        departmentRepo.save(newDepartment);
        return newDepartment;
    }
}
