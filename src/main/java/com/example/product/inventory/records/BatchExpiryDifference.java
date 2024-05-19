package com.example.product.inventory.records;

import lombok.Builder;
import lombok.Getter;

@Builder
public record BatchExpiryDifference (String stockCode,
    int years ,
                                     int months ,
                                     int days ){}
