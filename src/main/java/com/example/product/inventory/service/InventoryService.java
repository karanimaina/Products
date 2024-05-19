package com.example.product.inventory.service;

import com.example.product.commons.UniversalResponse;
import com.example.product.inventory.records.Inventory;
import reactor.core.publisher.Mono;

public interface InventoryService {
    //update productQuantity if product is present
    Mono<UniversalResponse>addProductInventory(Inventory inventory);
    Mono<UniversalResponse>sellProduct(String stockCode);



}
