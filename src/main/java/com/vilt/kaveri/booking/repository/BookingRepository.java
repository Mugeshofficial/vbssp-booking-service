package com.vilt.kaveri.booking.repository;

import com.vilt.kaveri.booking.entity.Booking;
import com.vilt.kaveri.booking.entity.enums.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findByUserId(Long userId);
    List<Booking> findBySellerId(Long sellerId);

    // Fixed method names - use 'id' instead of 'bookingId'
    Booking findByIdAndUserId(Long id, Long userId);
    Booking findByIdAndSellerId(Long id, Long sellerId);

    List<Booking> findByStatus(BookingStatus status);
}
