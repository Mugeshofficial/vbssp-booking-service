package com.vilt.kaveri.booking.service;

import com.vilt.kaveri.booking.entity.Booking;
import com.vilt.kaveri.booking.entity.Transaction;

import java.util.List;

public interface BookingService {

    Booking createBooking(Booking booking);
    Booking getBookingById(Long bookingId);
    List<Booking> getBookingsByUserId(Long userId);
    List<Booking> getBookingsBySellerId(Long sellerId);
    Booking cancelBooking(Long bookingId, Long userId);

    // Add the missing method
    Transaction getTransactionByBookingId(Long bookingId);
}