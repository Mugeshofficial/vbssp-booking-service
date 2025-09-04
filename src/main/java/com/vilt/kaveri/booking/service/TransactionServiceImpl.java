package com.vilt.kaveri.booking.service;

import com.vilt.kaveri.booking.entity.Booking;
import com.vilt.kaveri.booking.entity.Transaction;
import com.vilt.kaveri.booking.entity.enums.TransactionStatus;
import com.vilt.kaveri.booking.exception.BadRequestException;
import com.vilt.kaveri.booking.repository.BookingRepository;
import com.vilt.kaveri.booking.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final BookingRepository bookingRepository;

    @Override
    @Transactional
    public Transaction makePayment(Long bookingId, Long userId, BigDecimal amount) {
        Booking booking = bookingRepository.findByIdAndUserId(bookingId, userId);
        if (booking == null) {
            throw new BadRequestException("Booking not found for user");
        }

        Transaction transaction = Transaction.builder()
                .bookingId(bookingId)
                .userId(userId)
                .amount(amount)
                .status(amount.compareTo(booking.getCarPrice()) == 0 ? TransactionStatus.SUCCESS : TransactionStatus.FAILED)
                .build();

        return transactionRepository.save(transaction);
    }

    @Override
    public List<Transaction> getTransactionsByBookingId(Long bookingId, Long userId) {
        return transactionRepository.findByBookingIdAndUserId(bookingId, userId);
    }
}