package com.example.product.inventory.service;

import com.example.product.commons.UniversalResponse;
import com.example.product.inventory.exception.InventoryException;
import com.example.product.inventory.models.Batch;
import com.example.product.inventory.models.ProductInventory;
import com.example.product.inventory.records.Inventory;
import com.example.product.inventory.repository.BatchRepository;
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

import java.util.*;

@RequiredArgsConstructor
@Service
public class InventoryServiceImpl implements  InventoryService {
    private  final ProductRepository productRepository;
    private  final InventoryRepository inventoryRepository;
    private final BatchRepository batchRepository;
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
            Product product = productRepository.findById(inventory.productId())
                    .orElseThrow(() -> new InventoryException("Product not found"));
            /**
             * if product exist , check for it in the inventory
             */
            Optional<ProductInventory> productInventory= inventoryRepository.findByProduct(product);
            if(productInventory.isPresent()){
                /** create a new batch and update the batch collection
                 */
                Optional<Batch> checkBatch= batchRepository.findByStockCode(inventory.barcode());
                Batch batch = Batch.builder()
                        .product(product)
                        .purchaseDate(new Date())
                        .weight(inventory.weight())
                        .expiryDate(inventory.expiryDate())
                        .stockCode(inventory.barcode())
                        .quantity(inventory.quantity())
                        .amount(inventory.amount())
                        .build();
                if(checkBatch.isPresent()){
                    batchRepository.save(batch);
                }
                batchRepository.save(batch);
                ProductInventory productInventory1 = productInventory.get();
                productInventory1.getBatches().add(batch);
                inventoryRepository.save(productInventory1);
            }
            Batch batch = Batch.builder()
                    .product(product)
                    .purchaseDate(new Date())
                    .weight(inventory.weight())
                    .expiryDate(inventory.expiryDate())
                    .stockCode(inventory.barcode())
                    .quantity(inventory.quantity())
                    .amount(inventory.amount())
                    .build();
            // create  a product inventory object
            ProductInventory inventory1= ProductInventory
                    .builder()
                    .product(product)
                    .batches(List.of(batch))
                    .build();
            batchRepository.save(batch);
            inventory1.getBatches().add(batch);
            return UniversalResponse.builder()
                    .data(inventory1)
                    .message("Product inventory updated successfully")
                    .status(200)
                    .build();
        }).publishOn(Schedulers.boundedElastic());
            }

    @Override
    public Mono<UniversalResponse> sellProduct(String stockCode) {
        //retrieve batch based on stockCode

        return null;
    }


}



        }




