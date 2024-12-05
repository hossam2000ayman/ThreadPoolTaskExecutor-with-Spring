package com.example.ecommerceapplication.service;

import com.example.ecommerceapplication.controller.response.exception.ItemIsOutOfStockException;
import com.example.ecommerceapplication.model.Order;
import com.example.ecommerceapplication.model.enums.OrderStatus;
import com.example.ecommerceapplication.repository.OrderRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {
    final OrderRepository orderRepository;
    final InventoryService inventoryService;
    final PaymentService paymentService;

    public OrderService(OrderRepository orderRepository, InventoryService inventoryService, PaymentService paymentService) {
        this.orderRepository = orderRepository;
        this.inventoryService = inventoryService;
        this.paymentService = paymentService;
    }

    @Transactional
    public Order placeOrder(String customerName, Long itemId, Integer quantity) throws ItemIsOutOfStockException {
        Order order = new Order();
        order.setCustomerName(customerName);
        order.setItemId(itemId);
        order.setQuantity(quantity);

        if (inventoryService.validateStock(itemId, quantity)) {
            order.setStatus(OrderStatus.PROCESSING);
            orderRepository.save(order);
            //process payment asynchronously
            processOrderPaymentAsync(order);
        } else {
            order.setStatus(OrderStatus.FAILED);
            orderRepository.save(order);
            throw new ItemIsOutOfStockException("ItemId : " + itemId + " is out of the stock");
        }
        return order;
    }

    @Async("asyncExecutor")
    public void processOrderPaymentAsync(Order order) {
        boolean paymentSuccess = paymentService.processPayment(order.getId());
        if (paymentSuccess) {
            inventoryService.reduceStock(order.getItemId(), order.getQuantity());
            order.setStatus(OrderStatus.COMPLETED);
        } else {
            order.setStatus(OrderStatus.FAILED);
        }
        orderRepository.save(order);
    }
}
