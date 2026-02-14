package com.example.demonstration.abstracts;


import com.example.demonstration.dtos.LeaveRequestCreate;
import com.example.demonstration.entities.LeaveRequest;

import java.util.List;
import java.util.UUID;

public interface LeaveRequestService {

    LeaveRequest createOne(LeaveRequestCreate leaveRequestCreate, UUID employee_id);

    List<LeaveRequest> findAllByEmployeeId(UUID employee_id);

}
