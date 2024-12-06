package com.example.ecommerceapplication.model;

import com.example.ecommerceapplication.model.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "orders")
@Setter
@Getter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String customerName;
    Long itemId;
    Integer quantity;

    @Enumerated(EnumType.STRING)
    OrderStatus status;
}
