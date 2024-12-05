package com.example.ecommerceapplication.controller.response.exception;

public class ItemIsOutOfStockException extends Exception {
    public ItemIsOutOfStockException(String message) {
        super(message);
    }
}
