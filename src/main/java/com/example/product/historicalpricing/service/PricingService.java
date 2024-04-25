package com.example.product.historicalpricing.service;

import com.example.product.commons.UniversalResponse;
import com.example.product.historicalpricing.records.PricingData;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public interface PricingService {
Function<PricingData, UniversalResponse> addPricing ();
BiFunction<PricingData, Date, UniversalResponse> deletePricing ();
Function<Long,UniversalResponse>retrievePriceHistoryForAProduct();
}
