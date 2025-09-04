package com.vilt.kaveri.booking.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingRequestDTO {

    @NotNull(message = "User ID cannot be null")
    @Positive(message = "User ID must be positive")
    private Long userId;

    @NotNull(message = "Seller ID cannot be null")
    @Positive(message = "Seller ID must be positive")
    private Long sellerId;

    @NotNull(message = "Car listing ID cannot be null")
    @Positive(message = "Car listing ID must be positive")
    private Long carListingId;

    @NotNull(message = "Booking date cannot be null")
    @Future(message = "Booking date must be in the future")
    private LocalDate bookingDate;
}