package com.example.ecommerceapplication.controller;

import com.example.ecommerceapplication.controller.response.exception.ItemIsOutOfStockException;
import com.example.ecommerceapplication.model.Order;
import com.example.ecommerceapplication.service.OrderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/async/orders")

public class OrderController {

    final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("place")
    public Order placeOrder(@RequestParam String customerName, @RequestParam Long itemId, @RequestParam Integer quantity) throws ItemIsOutOfStockException {
        return orderService.placeOrder(customerName, itemId, quantity);
    }


}
