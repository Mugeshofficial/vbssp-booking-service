package com.vilt.kaveri.booking.dto;

import com.vilt.kaveri.booking.entity.Transaction;
import com.vilt.kaveri.booking.entity.enums.TransactionStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class TransactionResponseDTO {
    private Long transactionId;
    private Long bookingId;
    private Long userId;
    private BigDecimal amount;
    private TransactionStatus status;
    private LocalDateTime createdTime;

    public static TransactionResponseDTO fromEntity(Transaction transaction) {
        TransactionResponseDTO dto = new TransactionResponseDTO();
        dto.setTransactionId(transaction.getTransactionId());
        dto.setBookingId(transaction.getBookingId());
        dto.setUserId(transaction.getUserId());
        dto.setAmount(transaction.getAmount());
        dto.setStatus(transaction.getStatus());
        dto.setCreatedTime(transaction.getCreatedTime());
        return dto;
    }
}
