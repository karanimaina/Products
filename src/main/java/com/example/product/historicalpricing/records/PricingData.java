package com.example.product.historicalpricing.records;

import java.util.Date;

public record PricingData(Long manufacturerId, Long productId, Double price, Date changeDate) {
}
