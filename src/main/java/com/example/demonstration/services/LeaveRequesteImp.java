package com.example.demonstration.services;

import com.example.demonstration.abstracts.LeaveRequestService;
import com.example.demonstration.dtos.LeaveRequestCreate;
import com.example.demonstration.entities.Employee;
import com.example.demonstration.entities.LeaveRequest;
import com.example.demonstration.repositories.EmployeeRepo;
import com.example.demonstration.repositories.LeaveRequestRepo;
import com.example.demonstration.shared.CustomResponseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class LeaveRequesteImp implements LeaveRequestService {

    @Autowired
    private final EmployeeRepo employeeRepo;

    @Autowired
    private LeaveRequestRepo leaveRequestRepo;

    public LeaveRequesteImp(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    @Override
    public LeaveRequest createOne(LeaveRequestCreate leaveRequestCreate, UUID employee_id) {

        Employee employee = employeeRepo.findById(employee_id)
                .orElseThrow(() -> CustomResponseException.ResourceNotFound(
                        "Employee with ID " + employee_id + " not found"
                ));
        LeaveRequest leaveRequest = new LeaveRequest();
        leaveRequest.setStatus("PENDING");
        leaveRequest.setReason(leaveRequestCreate.reason());
        leaveRequest.setStartDate(leaveRequestCreate.startDate());
        leaveRequest.setEndDate(leaveRequestCreate.endDate());

        leaveRequest.setEmployee(employee);
        leaveRequestRepo.save(leaveRequest);

        return leaveRequest;
    }

    @Override
    public List<LeaveRequest> findAllByEmployeeId(UUID employee_id) {


        return leaveRequestRepo.findAllByEmployeeId(employee_id);
    }


}
