package com.example.product.product.repository;

import com.example.product.product.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository  extends JpaRepository<Product,Long> {

Optional<Product> findByNameIgnoreCaseAndCompany(String name, String company);
Optional<Product> findByNameIgnoreCase(String name);
}
