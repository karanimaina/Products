package com.example.product.historicalpricing.service;

import com.example.product.commons.UniversalResponse;

import java.util.function.BiFunction;
import java.util.function.Function;

public interface PricingService {
	BiFunction<Long,Long, UniversalResponse> addPricing();
	BiFunction<Long,Long, UniversalResponse>deletePricing();
}
