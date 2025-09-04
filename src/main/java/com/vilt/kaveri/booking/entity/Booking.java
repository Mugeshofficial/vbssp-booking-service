package com.vilt.kaveri.booking.entity;

import com.vilt.kaveri.booking.entity.enums.BookingStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "bookings")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private Long sellerId;
    private Long carListingId;
    private LocalDate bookingDate;
    private BigDecimal carPrice;

    @Enumerated(EnumType.STRING)
    private BookingStatus status; // Use enum here
}
