package com.vilt.kaveri.booking.util;
//optional
import java.util.Objects;

public class ValidationUtil {

    private ValidationUtil() {
        // Utility class
    }

    public static void requireNonNull(Object obj, String message) {
        if (Objects.isNull(obj)) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void requirePositive(Number number, String message) {
        if (number == null || number.doubleValue() <= 0) {
            throw new IllegalArgumentException(message);
        }
    }
}
