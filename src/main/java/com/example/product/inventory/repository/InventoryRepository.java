package com.example.product.inventory.repository;

import com.example.product.inventory.models.ProductInventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository  extends JpaRepository<ProductInventory, Long> {

}
