package com.vilt.kaveri.booking.service;

import com.vilt.kaveri.booking.client.CarListingClient;
import com.vilt.kaveri.booking.entity.Booking;
import com.vilt.kaveri.booking.entity.Transaction;
import com.vilt.kaveri.booking.entity.enums.BookingStatus;
import com.vilt.kaveri.booking.exception.BadRequestException;
import com.vilt.kaveri.booking.exception.ResourceNotFoundException;
import com.vilt.kaveri.booking.repository.BookingRepository;
import com.vilt.kaveri.booking.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final TransactionRepository transactionRepository;
    private final CarListingClient carListingClient;

    @Override
    public Booking createBooking(Booking booking) {
        BigDecimal carPrice = carListingClient.getCarPrice(booking.getCarListingId());
        booking.setCarPrice(carPrice);
        return bookingRepository.save(booking);
    }

    @Override
    public Booking getBookingById(Long bookingId) {
        return bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found with id: " + bookingId));
    }

    @Override
    public List<Booking> getBookingsByUserId(Long userId) {
        return bookingRepository.findByUserId(userId);
    }

    @Override
    public List<Booking> getBookingsBySellerId(Long sellerId) {
        return bookingRepository.findBySellerId(sellerId);
    }

    @Override
    @Transactional
    public Booking cancelBooking(Long bookingId, Long userId) {
        Booking booking = bookingRepository.findByIdAndUserId(bookingId, userId);
        if (booking == null) {
            throw new ResourceNotFoundException("Booking not found for user and bookingId");
        }
        return bookingRepository.save(booking);
    }

    @Override
    public Transaction getTransactionByBookingId(Long bookingId) {
        List<Transaction> transactions = transactionRepository.findByBookingId(bookingId);
        return transactions.isEmpty() ? null : transactions.get(0); // Return first transaction or null
    }
}