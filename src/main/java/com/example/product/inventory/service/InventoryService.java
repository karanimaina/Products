package com.example.product.inventory.service;

import com.example.product.commons.UniversalResponse;
import com.example.product.inventory.records.Inventory;
import com.example.product.inventory.records.StockValuationResponse;
import reactor.core.publisher.Mono;

public interface InventoryService {
    Mono<UniversalResponse>addProductInventory(Inventory inventory);
    Mono<UniversalResponse> sellProduct(String stockCode,int quantity);
    Mono<UniversalResponse>updateOnProductExpiry();
    Mono<StockValuationResponse> calculateStockValuation();
}
