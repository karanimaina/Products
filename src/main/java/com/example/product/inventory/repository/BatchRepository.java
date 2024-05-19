package com.example.product.inventory.repository;

import com.example.product.inventory.models.Batch;

import com.example.product.product.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface BatchRepository  extends JpaRepository<Batch,Long> {


    Optional<Batch> findByStockCode(String barcode);

   List<Batch> findByProduct(Product product);
}
