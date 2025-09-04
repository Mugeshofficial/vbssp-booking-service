package com.vilt.kaveri.booking.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;

@FeignClient(name = "car-listing-service", url = "http://localhost:8081") // Replace with Eureka service name later
public interface CarListingClient {

    @GetMapping("/cars/{carListingId}/price")
    BigDecimal getCarPrice(@PathVariable("carListingId") Long carListingId);
}
