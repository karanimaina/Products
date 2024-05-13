package com.example.product.inventory.service;

import com.example.product.commons.UniversalResponse;
import com.example.product.inventory.exception.InventoryException;
import com.example.product.inventory.models.ProductInventory;
import com.example.product.inventory.records.Inventory;
import com.example.product.inventory.repository.InventoryRepository;
import com.example.product.product.models.Product;
import com.example.product.product.repository.ProductRepository;
import io.netty.util.concurrent.UnorderedThreadPoolEventExecutor;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class InventoryServiceImpl implements  InventoryService {
    private  final ProductRepository productRepository;
    private  final InventoryRepository inventoryRepository;
    /**
     * @Authpr Felix Maina
     * check if a product exist by id  from productRepos and InventoryRepos
     * if exist in productRepos and doesnot exist in inventoryRepos  create anew instance
     * if alreadyexist in  inventoryRepos create a cumulate the  quantiry present
     *
     * @param inventory
     * @return
     */
    @Override
    public Mono<UniversalResponse> addProductInventory(Inventory inventory) {
        return Mono.fromCallable(()->{
            Product product = productRepository .findById(inventory.productId()).orElse(null);
            ProductInventory productInventory1;
            if (product == null) {
                throw  new InventoryException("product not found");
            }else {
                Optional<ProductInventory> productInventory = inventoryRepository.findById(inventory.productId());
                if (productInventory.isPresent()) {
                  productInventory1 = productInventory.get();
                    int quantity = productInventory1.getQuantity();
                    int newQuantity = quantity + inventory.quantity();
                    productInventory1.setQuantity(newQuantity);
                    inventoryRepository.save(productInventory1);
                } else {
                    throw new InventoryException("product not found");
                }
            }
            return UniversalResponse.builder()
                    .data(productInventory1)
                    .message("product created successfully")
                    .status(200).build();
        }).publishOn(Schedulers.boundedElastic());


            }



        }




