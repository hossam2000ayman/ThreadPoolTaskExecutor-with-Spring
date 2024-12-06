package com.example.ecommerceapplication.service;

import com.example.ecommerceapplication.model.Inventory;
import com.example.ecommerceapplication.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InventoryService {
    final InventoryRepository inventoryRepository;


    public boolean validateStock(Long itemId, Integer quantity) {
        Optional<Inventory> inventory = inventoryRepository.findById(itemId);
        return inventory.map(inv -> inv.getStock() >= quantity).orElse(false);
    }

    public void reduceStock(Long itemId, Integer quantity) {
        Inventory inventory = inventoryRepository.findById(itemId).orElseThrow(() -> new IllegalArgumentException("Item not found"));
        inventory.setStock(inventory.getStock() - quantity);
        inventoryRepository.saveAndFlush(inventory);
    }
}
