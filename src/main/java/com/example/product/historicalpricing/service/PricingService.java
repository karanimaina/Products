package com.example.product.historicalpricing.service;

import com.example.product.commons.UniversalResponse;
import com.example.product.historicalpricing.records.PricingData;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public interface PricingService {
Mono<Function<PricingData, UniversalResponse>> addPricing ();
Mono<BiFunction<PricingData, Date, UniversalResponse>> deletePricing ();
Mono<Function<Long,UniversalResponse>>retrievePriceHistoryForAProduct();
}
