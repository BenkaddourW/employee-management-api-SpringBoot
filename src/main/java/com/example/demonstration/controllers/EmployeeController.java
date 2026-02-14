package com.example.demonstration.controllers;

import com.example.demonstration.abstracts.EmployeeService;
import com.example.demonstration.abstracts.LeaveRequestService;
import com.example.demonstration.dtos.EmployeeCreate;
import com.example.demonstration.dtos.EmployeeUpdate;
import com.example.demonstration.dtos.LeaveRequestCreate;
import com.example.demonstration.entities.Employee;
import com.example.demonstration.entities.LeaveRequest;
import com.example.demonstration.shared.GlobalResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private LeaveRequestService leaveRequestService;

    @GetMapping
    public ResponseEntity<GlobalResponse<List<Employee>>> findAll() {
        List<Employee> employees = employeeService.findAll();

        return new ResponseEntity<>(new GlobalResponse<>(employees), HttpStatus.OK);
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<GlobalResponse<Employee>> findOne(@PathVariable UUID employeeId) {

        Employee employee = employeeService.findOne(employeeId);


        return new ResponseEntity<>(new GlobalResponse<>(employee), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<GlobalResponse<Employee>> createOne(@RequestBody @Valid EmployeeCreate employee) {
        Employee createEmployee = employeeService.createOne(employee);
        ;

        return new ResponseEntity<>(new GlobalResponse<>(createEmployee), HttpStatus.CREATED);
    }

    @DeleteMapping("/{employeeId}")
    public ResponseEntity<Optional<Employee>> deleteOne(@PathVariable UUID employeeId) {
        employeeService.deleteOne(employeeId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{employeeId}")
    public ResponseEntity<GlobalResponse<Employee>> updateOne(@PathVariable UUID employeeId,
                                                              @RequestBody @Valid EmployeeUpdate employee) {
        Employee updateEmployee = employeeService.updateOne(employeeId, employee);
        return new ResponseEntity<>(new GlobalResponse<>(updateEmployee), HttpStatus.OK);
    }


    @PostMapping("/{employeeId}/leave-request")
    public ResponseEntity<GlobalResponse<LeaveRequest>> leaveRequest
            (@RequestBody @Valid LeaveRequestCreate leaveRequest, @PathVariable UUID employeeId) {
        LeaveRequest newLeaveRequest = leaveRequestService.createOne(leaveRequest, employeeId);

        return new ResponseEntity<>(new GlobalResponse<>(newLeaveRequest), HttpStatus.CREATED);
    }

    @GetMapping("/{employeeId}/leave-requests")

    public ResponseEntity<GlobalResponse<List<LeaveRequest>>> leaveRequestsByEmpoyeeId
            (@PathVariable UUID employeeId) {

        List<LeaveRequest> leaveRequests = leaveRequestService.findAllByEmployeeId(employeeId);

        return new ResponseEntity<>(new GlobalResponse<>(leaveRequests), HttpStatus.OK);
    }
}
