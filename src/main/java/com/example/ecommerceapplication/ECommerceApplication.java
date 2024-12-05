package com.example.ecommerceapplication;

import com.example.ecommerceapplication.model.Inventory;
import com.example.ecommerceapplication.repository.InventoryRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class ECommerceApplication {
    final InventoryRepository inventoryRepository;

    public ECommerceApplication(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(ECommerceApplication.class, args);
    }


    @Bean
    public CommandLineRunner runner() {
        return (args) -> {
            Inventory inventory1 = new Inventory();
            inventory1.setItemName("Item 1");
            inventory1.setStock(3);
            Inventory inventory2 = new Inventory();
            inventory2.setItemName("Item 2");
            inventory2.setStock(6);
            Inventory inventory3 = new Inventory();
            inventory3.setItemName("Item 3");
            inventory3.setStock(8);
            Inventory inventory4 = new Inventory();
            inventory4.setItemName("Item 4");
            inventory4.setStock(4);
            inventoryRepository.saveAll(List.of(inventory1, inventory2, inventory3, inventory4));
        };
    }
}
