package com.vilt.kaveri.booking.service;

import com.vilt.kaveri.booking.entity.Transaction;

import java.math.BigDecimal;
import java.util.List;

public interface TransactionService {

    Transaction makePayment(Long bookingId, Long userId, BigDecimal amount);

    List<Transaction> getTransactionsByBookingId(Long bookingId, Long userId);
}
