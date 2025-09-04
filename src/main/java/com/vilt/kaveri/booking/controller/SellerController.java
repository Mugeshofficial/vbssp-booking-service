package com.vilt.kaveri.booking.controller;

import com.vilt.kaveri.booking.dto.BookingResponseDTO;
import com.vilt.kaveri.booking.entity.Booking;
import com.vilt.kaveri.booking.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/bookings/seller")
@RequiredArgsConstructor
public class SellerController {

    private final BookingService bookingService;

    // Get all bookings for a seller
    @GetMapping("/{sellerId}")
    @PreAuthorize("hasRole('SELLER')")
    public ResponseEntity<List<BookingResponseDTO>> getBookingsBySeller(@PathVariable Long sellerId) {
        List<Booking> bookings = bookingService.getBookingsBySellerId(sellerId);
        List<BookingResponseDTO> responses = bookings.stream()
                .map(booking -> {
                    var transaction = bookingService.getTransactionByBookingId(booking.getId());
                    return BookingResponseDTO.fromEntity(booking, transaction);
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }
}
