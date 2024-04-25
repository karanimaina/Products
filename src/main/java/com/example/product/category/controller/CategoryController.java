package com.example.product.category.controller;

import com.example.product.category.functions.CategoryCreationFunction;
import com.example.product.category.records.CategoryRecord;
import com.example.product.category.service.CategoryService;
import com.example.product.commons.UniversalResponse;
import com.example.product.product.exceptions.ProductException;
import com.example.product.product.records.CustomPage;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.support.ClientResponseWrapper;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/category")
public class CategoryController {
private  final CategoryService categoryService;
@PostMapping("/add")
 ResponseEntity<UniversalResponse>addCategory(@RequestBody CategoryRecord categoryRecord){
	 return ResponseEntity.ok (categoryService.createCategory ().apply (categoryRecord));
 }
@PostMapping("/delete")
ResponseEntity<UniversalResponse>deleteCategory(@RequestParam("id") Long id){
	return ResponseEntity.ok (categoryService.deleteCategory ().apply (id));
}
@PostMapping("/view")
ResponseEntity<UniversalResponse>viewCategory(@RequestBody CustomPage customPage){
	return ResponseEntity.ok (categoryService.viewAllCategory ().apply (customPage));
}
@PostMapping("/addToCategory")
ResponseEntity<UniversalResponse>viewCategory(@RequestParam("productId")Long productId, @RequestParam("categoryId") Long categoryId){
	return ResponseEntity.ok (categoryService.addProductsToCategory ().apply (productId, categoryId));
}
 }


