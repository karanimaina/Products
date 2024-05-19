package com.example.product.inventory.repository;

import com.example.product.inventory.models.Batch;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BatchRepository  extends JpaRepository<Batch,Long> {
    Optional<Batch> findByStockCode(String stockCode);
}
