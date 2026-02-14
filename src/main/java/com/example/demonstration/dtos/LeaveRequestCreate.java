package com.example.demonstration.dtos;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record LeaveRequestCreate(

        @NotNull(message = "Start date is required")
        @FutureOrPresent(message = "start date should be now or in the future")
        LocalDate startDate,

        @NotNull(message = "End date is required")
        @FutureOrPresent(message = "end date should be now or in the future")
        LocalDate endDate,

        @NotBlank(message = "Reason is required")
        @Size(min = 2, max = 100, message = "Reason must be between 2 and 50 characters")
        String reason


) {
}
