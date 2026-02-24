package com.example.demonstration.controllers;


import com.example.demonstration.dtos.LoginRequest;
import com.example.demonstration.dtos.SignupRequest;
import com.example.demonstration.services.AuthService;
import com.example.demonstration.shared.GlobalResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<GlobalResponse<String>> signup(@RequestBody SignupRequest signupRequest) {

        authService.signup(signupRequest);
        System.out.printf("signup request: %s", signupRequest.toString());
        return new ResponseEntity<>(new GlobalResponse<>("Signed up"), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<GlobalResponse<String>> login(@RequestBody LoginRequest loginRequest) {

        String token = authService.login(loginRequest);
        return new ResponseEntity<>(new GlobalResponse<>(token), HttpStatus.OK);
    }
}
