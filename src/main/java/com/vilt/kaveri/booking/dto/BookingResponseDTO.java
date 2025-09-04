package com.vilt.kaveri.booking.dto;

import com.vilt.kaveri.booking.entity.Booking;
import com.vilt.kaveri.booking.entity.Transaction;
import com.vilt.kaveri.booking.entity.enums.BookingStatus;
import com.vilt.kaveri.booking.entity.enums.TransactionStatus;
import lombok.*;

        import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingResponseDTO {

    private Long bookingId;
    private Long userId;
    private Long sellerId;
    private Long carListingId;
    private BigDecimal carPrice;
    private LocalDateTime bookingDate;
    private BookingStatus status;
    private String message;

    // Single entity mapping (without transaction)
    public static BookingResponseDTO fromEntity(Booking booking) {
        BookingResponseDTO dto = new BookingResponseDTO();
        dto.setBookingId(booking.getId());
        dto.setUserId(booking.getUserId());
        dto.setSellerId(booking.getSellerId());
        dto.setCarListingId(booking.getCarListingId());
        dto.setCarPrice(booking.getCarPrice()); // Now both are BigDecimal
        dto.setBookingDate(booking.getBookingDate().atStartOfDay());
        dto.setStatus(booking.getStatus() != null ? booking.getStatus() : BookingStatus.PENDING); // Use entity status
        dto.setMessage("Booking details fetched successfully");
        return dto;
    }

    // Mapping with both Booking and Transaction entities
    public static BookingResponseDTO fromEntity(Booking booking, Transaction transaction) {
        BookingResponseDTO dto = fromEntity(booking);

        if (transaction != null) {
            dto.setStatus(mapTransactionStatusToBookingStatus(transaction.getStatus()));
        }

        return dto;
    }

    private static BookingStatus mapTransactionStatusToBookingStatus(TransactionStatus transactionStatus) {
        if (transactionStatus == null) return BookingStatus.PENDING;

        switch (transactionStatus) {
            case SUCCESS:
                return BookingStatus.CONFIRMED;
            case FAILED:
                return BookingStatus.CANCELLED;
            default:
                return BookingStatus.PENDING;
        }
    }
}
