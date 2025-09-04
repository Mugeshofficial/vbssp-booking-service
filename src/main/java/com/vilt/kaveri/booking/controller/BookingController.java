package com.vilt.kaveri.booking.controller;

import com.vilt.kaveri.booking.dto.BookingRequestDTO;
import com.vilt.kaveri.booking.dto.BookingResponseDTO;
import com.vilt.kaveri.booking.entity.Booking;
import com.vilt.kaveri.booking.service.BookingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<BookingResponseDTO> createBooking(@Valid @RequestBody BookingRequestDTO requestDTO) {
        Booking booking = Booking.builder()
                .userId(requestDTO.getUserId())
                .sellerId(requestDTO.getSellerId())
                .carListingId(requestDTO.getCarListingId())
                .bookingDate(requestDTO.getBookingDate())
                .build();

        Booking savedBooking = bookingService.createBooking(booking);
        BookingResponseDTO response = BookingResponseDTO.fromEntity(savedBooking);
        response.setMessage("Booking created. Please pay full amount " + savedBooking.getCarPrice());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{bookingId}")
    @PreAuthorize("hasRole('USER') or hasRole('SELLER')")
    public ResponseEntity<BookingResponseDTO> getBookingById(@PathVariable Long bookingId) {
        Booking booking = bookingService.getBookingById(bookingId);
        return ResponseEntity.ok(BookingResponseDTO.fromEntity(booking));
    }

    @GetMapping("/user/{userId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<BookingResponseDTO>> getBookingsByUser(@PathVariable Long userId) {
        List<Booking> bookings = bookingService.getBookingsByUserId(userId);
        List<BookingResponseDTO> responses = bookings.stream()
                .map(BookingResponseDTO::fromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @PutMapping("/{bookingId}/cancel")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<BookingResponseDTO> cancelBooking(@PathVariable Long bookingId,
                                                            @RequestParam Long userId) {
        Booking cancelledBooking = bookingService.cancelBooking(bookingId, userId);
        BookingResponseDTO response = BookingResponseDTO.fromEntity(cancelledBooking);
        response.setMessage("Booking cancelled successfully");
        return ResponseEntity.ok(response);
    }
}