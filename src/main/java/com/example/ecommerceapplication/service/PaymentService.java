package com.example.ecommerceapplication.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PaymentService {

    public boolean processPayment(Long orderId) {
        //Simulate payment processing with a delay
        try {
            Thread.sleep(2000);
            System.out.println("Payment processed for order: " + orderId);
            return true;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }
}
