package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.CheckoutRequest;
import com.example.demo.services.OrderServices;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
public class Checkoutcontroller {

    @Autowired
    private OrderServices orderService;

    @PostMapping("/checkout")
    public ResponseEntity<?> checkout(@AuthenticationPrincipal UserDetails userDetails, @RequestBody CheckoutRequest request) {

        // Get the username from the authenticated user principal
        String userId = userDetails.getUsername();

        if (request.getItems() == null || request.getItems().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cart is empty.");
        }

        try {
            orderService.saveOrder(userId, request.getTotalAmount(), request.getItems());
            return ResponseEntity.ok().body("{\"message\": \"Order placed successfully\"}");

        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"message\": \"Error processing order data.\"}");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"message\": \"Error placing order.\"}");
        }
    }
}