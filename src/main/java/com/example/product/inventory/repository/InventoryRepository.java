package com.example.product.inventory.repository;

import com.example.product.inventory.models.ProductInventory;
import com.example.product.product.models.Product;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InventoryRepository  extends JpaRepository<ProductInventory, Long> {
    Optional<ProductInventory>findByProduct(Product product);
}
