package com.vilt.kaveri.booking.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

@FeignClient(name = "user-service", url = "http://localhost:8082") // Optional service
public interface UserServiceClient {

    @GetMapping("/users/{userId}")
    Map<String, Object> getUserDetails(@PathVariable("userId") Long userId);
}
