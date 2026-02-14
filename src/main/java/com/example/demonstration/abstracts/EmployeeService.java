package com.example.demonstration.abstracts;

import com.example.demonstration.dtos.EmployeeCreate;
import com.example.demonstration.dtos.EmployeeUpdate;
import com.example.demonstration.entities.Employee;

import java.util.List;
import java.util.UUID;

public interface EmployeeService {

    Employee findOne(UUID employeeId);

    List<Employee> findAll();

    Employee createOne(EmployeeCreate employee);

    void deleteOne(UUID employeeId);

    Employee updateOne(UUID employeeId, EmployeeUpdate employee);
}
