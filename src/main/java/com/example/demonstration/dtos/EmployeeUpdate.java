package com.example.demonstration.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record EmployeeUpdate(@NotBlank(message = "first name is required")
                             @Size(min = 2, max = 50, message = "first name must be between 2 and 50 characters")
                             String firstName,

                             @NotBlank(message = "last name is required")
                             @Size(min = 2, max = 50, message = "last name must be between 2 and 50 characters")
                             String lasteName,


                             @NotBlank(message = "phone number is required")
                             @Pattern(
                                     regexp = "^[0-9+\\-() ]{8,20}$",
                                     message = "phone number format is invalid"
                             )
                             String phoneNumber,


                             @NotBlank(message = "position is required")
                             @Size(min = 2, max = 50, message = "position must be between 2 and 50 characters")
                             String position
) {
}
