package com.example.demonstration.dtos;

import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.UUID;

public record EmployeeCreate(

        @NotBlank(message = "first name is required")
        @Size(min = 2, max = 50, message = "first name must be between 2 and 50 characters")
        String firstName,

        @NotBlank(message = "last name is required")
        @Size(min = 2, max = 50, message = "last name must be between 2 and 50 characters")
        String lasteName,

        @NotBlank(message = "email is required")
        @Email(message = "email format is invalid")
        String email,

        @NotBlank(message = "phone number is required")
        @Pattern(
                regexp = "^[0-9+\\-() ]{8,20}$",
                message = "phone number format is invalid"
        )
        String phoneNumber,

        @NotNull(message = "hire date is required")
        @PastOrPresent(message = "hire date cannot be in the future")
        LocalDate hireDate,

        @NotBlank(message = "position is required")
        @Size(min = 2, max = 50, message = "position must be between 2 and 50 characters")
        String position,

        @NotNull(message = "departmentId is required")
//        @Size(min = 2, max = 50, message = "position must be between 2 and 50 characters")

        UUID departmentId
) {
}
