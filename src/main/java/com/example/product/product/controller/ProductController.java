package com.example.product.product.controller;

import com.example.product.commons.UniversalResponse;
import com.example.product.product.service.ProductService;
import com.example.product.product.records.CustomPage;
import com.example.product.product.records.ProductId;
import com.example.product.product.records.ProductInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping ("/api/v1/products")
public class ProductController {
private final ProductService productService;

@PostMapping ("/create")
ResponseEntity<UniversalResponse> createProduct (@RequestBody ProductInfo productInfo) {
	return ResponseEntity.ok (productService.createProduct ().apply (productInfo));
}

@PostMapping ("/update")
ResponseEntity<UniversalResponse> updateProduct (@RequestBody ProductId productId) {
	return ResponseEntity.ok (productService.updateProduct ().apply (productId));
}

@PostMapping ("/delete")
ResponseEntity<UniversalResponse> deleteProduct (@RequestBody Long productId) {
	return ResponseEntity.ok (productService.deleteProduct ().apply (productId));
}

@PostMapping ("/retrieve")
ResponseEntity<UniversalResponse> retrieveProduct (@RequestBody CustomPage customPage) {
	return ResponseEntity.ok (productService.viewProducts ().apply (customPage));
}

@PostMapping ("/retrieve/{id}")
ResponseEntity<UniversalResponse> retrieveProductById (@PathVariable Long id) {
	return ResponseEntity.ok (productService.fetchById ().apply (id));
}
}
