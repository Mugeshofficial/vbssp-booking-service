package com.vilt.kaveri.booking.controller;

import com.vilt.kaveri.booking.dto.TransactionRequestDTO;
import com.vilt.kaveri.booking.dto.TransactionResponseDTO;
import com.vilt.kaveri.booking.entity.Transaction;
import com.vilt.kaveri.booking.service.TransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/bookings/{bookingId}/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<TransactionResponseDTO> makePayment(@PathVariable Long bookingId,
                                                              @Valid @RequestBody TransactionRequestDTO requestDTO) {
        Transaction transaction = transactionService.makePayment(bookingId, requestDTO.getUserId(), requestDTO.getAmount());
        return ResponseEntity.ok(TransactionResponseDTO.fromEntity(transaction));
    }

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<TransactionResponseDTO>> getTransactions(@PathVariable Long bookingId,
                                                                        @RequestParam Long userId) {
        List<Transaction> transactions = transactionService.getTransactionsByBookingId(bookingId, userId);
        List<TransactionResponseDTO> responses = transactions.stream()
                .map(TransactionResponseDTO::fromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }
}