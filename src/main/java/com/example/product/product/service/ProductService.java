package com.example.product.product.service;

import com.example.product.commons.UniversalResponse;
import com.example.product.product.records.CustomPage;
import com.example.product.product.records.ProductId;
import com.example.product.product.records.ProductInfo;

import java.util.function.Function;

public interface ProductService {
	Function<ProductInfo, UniversalResponse>createProduct();
	Function<ProductId, UniversalResponse>updateProduct();
	Function<Long, UniversalResponse>deleteProduct();
     Function<CustomPage,UniversalResponse> viewProducts ();
	Function<Long, UniversalResponse>fetchById();
}
