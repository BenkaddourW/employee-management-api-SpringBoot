package com.example.demonstration.dtos;

import jakarta.validation.constraints.NotNull;

public record DepartmentCreate(
        @NotNull(message = "departmentId is required")

//        @Size(min = 2, max = 50, message = " name must be between 2 and 50 characters")
        String name) {
}
