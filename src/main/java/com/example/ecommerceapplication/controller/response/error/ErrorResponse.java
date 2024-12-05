package com.example.ecommerceapplication.controller.response.error;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;


public class ErrorResponse {
    String message;
    HttpStatus status;
    int statusCode;
    String path;
    LocalDateTime timeStamp;


    public void setMessage(String message) {
        this.message = message;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }


    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public ErrorResponse() {
    }
}
