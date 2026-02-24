package com.example.demonstration.services;

import com.example.demonstration.abstracts.EmployeeService;
import com.example.demonstration.dtos.EmployeeCreate;
import com.example.demonstration.dtos.EmployeeUpdate;
import com.example.demonstration.entities.Department;
import com.example.demonstration.entities.Employee;
import com.example.demonstration.repositories.DepartmenRepo;
import com.example.demonstration.repositories.EmployeeRepo;
import com.example.demonstration.shared.CustomResponseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    ArrayList<Employee> employees = new ArrayList<>();

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private DepartmenRepo departmenRepo;


    @Override
    @PreAuthorize("@securityUtils.isOwner(#employeeId)")
    public Employee findOne(UUID employeeId) {
        Employee employee = employeeRepo.findById(employeeId)
                .orElseThrow(() -> CustomResponseException.ResourceNotFound(
                        "Employee with ID " + employeeId + " not found"
                ));
        return employee;
    }

    @Override
    public List<Employee> findAll() {

        return employeeRepo.findAll();
    }

    @Override
    public Employee createOne(EmployeeCreate employeeCreate) {
        Employee employee = new Employee();
        Department department = departmenRepo.findById(employeeCreate.departmentId())
                .orElseThrow(() -> CustomResponseException.ResourceNotFound(
                        "Employee with ID " + employeeCreate.departmentId() + " not found"
                ));
        employee.setFirstName(employeeCreate.firstName());
        employee.setLasteName(employeeCreate.lasteName());
        employee.setEmail(employeeCreate.email());
        employee.setPhoneNumber(employeeCreate.phoneNumber());
        employee.setHireDate(employeeCreate.hireDate());
        employee.setPosition(employeeCreate.position());
        employee.setDepartment(department);
//        employees.add(employee);
        employeeRepo.save(employee);
        return employee;
    }


    @Override
    public void deleteOne(UUID employeeId) {
        Employee employee = employeeRepo.findById(employeeId)
                .orElseThrow(() -> CustomResponseException.ResourceNotFound(
                        "Employee with ID " + employeeId + " not found"
                ));
        employeeRepo.delete(employee);
    }


    @Override
    @PreAuthorize("@securityUtils.isOwner(#employeeId)")
    public Employee updateOne(UUID employeeId, EmployeeUpdate employee) {
        Employee existingEmployee = employeeRepo.findById(employeeId)
                .orElseThrow(() -> CustomResponseException.ResourceNotFound(
                        "Employee with ID " + employeeId + " not found"
                ));

        existingEmployee.setFirstName(employee.firstName());
        existingEmployee.setLasteName(employee.lasteName());

        existingEmployee.setPhoneNumber(employee.phoneNumber());
        existingEmployee.setPosition(employee.position());

        employeeRepo.save(existingEmployee);
        return existingEmployee;
    }
}
