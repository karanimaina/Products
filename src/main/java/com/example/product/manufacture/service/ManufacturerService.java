package com.example.product.manufacture.service;

import com.example.product.commons.UniversalResponse;
import com.example.product.manufacture.record.ManufacturerInfo;
import com.example.product.product.records.CustomPage;
import com.example.product.product.records.ProductId;
import com.example.product.product.records.ProductInfo;

import java.util.function.BiFunction;
import java.util.function.Function;

public interface ManufacturerService {
	Function<ManufacturerInfo, UniversalResponse> createManufacturer();
     BiFunction<Long,ManufacturerInfo, UniversalResponse> updateManufacturer();
	Function<Long, UniversalResponse>deleteManufacturer();
	Function<CustomPage,UniversalResponse> viewManufacturer ();
	Function<Long, UniversalResponse>fetchById();
	
}


