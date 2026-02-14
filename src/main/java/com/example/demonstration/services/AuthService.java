package com.example.demonstration.services;

import com.example.demonstration.dtos.SignupRequest;
import com.example.demonstration.entities.Employee;
import com.example.demonstration.entities.UserAccount;
import com.example.demonstration.repositories.EmployeeRepo;
import com.example.demonstration.repositories.UserAccountRepo;
import com.example.demonstration.shared.CustomResponseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserAccountRepo userAccountRepo;

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void singup(SignupRequest signupRequest) {
        Employee employee = employeeRepo.findById(signupRequest.employeeId())
                .orElseThrow(() -> CustomResponseException.ResourceNotFound(
                        "Employee with ID " + signupRequest.employeeId() + " not found"
                ));
        UserAccount account = new UserAccount();


        account.setUserName(signupRequest.username());
        account.setPassword(passwordEncoder.encode(signupRequest.password()));
        account.setEmployee(employee);
        userAccountRepo.save(account);


    }
}
