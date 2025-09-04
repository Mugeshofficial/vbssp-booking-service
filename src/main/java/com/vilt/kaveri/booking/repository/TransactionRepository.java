package com.vilt.kaveri.booking.repository;

import com.vilt.kaveri.booking.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    // Find all transactions for a specific booking
    List<Transaction> findByBookingId(Long bookingId);

    // Find transactions for a specific booking and user
    List<Transaction> findByBookingIdAndUserId(Long bookingId, Long userId);
}
