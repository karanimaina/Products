package com.example.product.inventory.records;

import lombok.Builder;
import lombok.Data;


@Builder
public record StockValuationResponse (int totalBuyingPrice ,
                                      int totalSellingPrice ,
                                      int totalProfit ){


}