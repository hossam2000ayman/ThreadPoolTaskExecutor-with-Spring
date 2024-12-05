package com.example.ecommerceapplication.controller.response.handler;

import com.example.ecommerceapplication.controller.response.error.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.DataFormatException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneralException(Exception e, WebRequest request) {
        ErrorResponse response = new ErrorResponse();

        response.setMessage(e.getMessage());
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.setPath(request.getDescription(false));
        response.setTimeStamp(LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<ErrorResponse> handleIOException(IOException e, WebRequest request) {
        ErrorResponse response = new ErrorResponse();
        response.setMessage(e.getMessage());
        response.setStatus(HttpStatus.BAD_REQUEST);
        response.setStatusCode(HttpStatus.BAD_REQUEST.value());
        response.setPath(request.getDescription(false));
        response.setTimeStamp(LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataFormatException.class)
    public ResponseEntity<ErrorResponse> handleDataFormatException(DataFormatException e, WebRequest request) {
        ErrorResponse response = new ErrorResponse();
        response.setMessage(e.getMessage());
        response.setStatus(HttpStatus.UNPROCESSABLE_ENTITY);
        response.setStatusCode(HttpStatus.UNPROCESSABLE_ENTITY.value());
        response.setPath(request.getDescription(false));
        response.setTimeStamp(LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValid(MethodArgumentNotValidException e, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
        ErrorResponse response = new ErrorResponse();
        response.setMessage(e.getMessage());
        response.setStatus(HttpStatus.BAD_REQUEST);
        response.setStatusCode(HttpStatus.BAD_REQUEST.value());
        response.setPath(request.getDescription(false));
        response.setTimeStamp(LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
