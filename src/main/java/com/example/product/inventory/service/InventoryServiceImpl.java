package com.example.product.inventory.service;

import com.example.product.commons.UniversalResponse;
import com.example.product.inventory.exception.InventoryException;
import com.example.product.inventory.models.Batch;
import com.example.product.inventory.models.ProductInventory;
import com.example.product.inventory.records.BatchExpiryDifference;
import com.example.product.inventory.records.Inventory;
import com.example.product.inventory.records.ProductExpiryDetails;
import com.example.product.inventory.records.StockValuationResponse;
import com.example.product.inventory.repository.BatchRepository;
import com.example.product.inventory.repository.InventoryRepository;
import com.example.product.product.models.Product;
import com.example.product.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

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
                        .buyingPrice(inventory.buying())
                        .sellingPrice(inventory.selling())
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
                    .buyingPrice(inventory.buying())
                    .sellingPrice(inventory.selling())
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
   /**
     * Sells a specified quantity of a product identified by its stock code.
     *
     * This method performs the following steps:
     * 1. Retrieves the batch associated with the given stock code.
     * 2. Checks if the batch exists; if not, throws an InventoryException.
     * 3. Retrieves the product inventory associated with the product in the batch.
     * 4. Checks if the product inventory exists; if not, throws an InventoryException.
     * 5. Decreases the quantity of the batch by the specified amount.
     * 6. Updates the total quantity of the product inventory based on the current batch quantities.
     * 7. Saves the updated batch and product inventory to their respective repositories.
     * 8. Returns a UniversalResponse object containing the updated product inventory and a success message.
     *
     * @param stockCode the stock code of the batch to be sold.
     * @param quantity the quantity of the product to be sold.
     * @return a Mono emitting a UniversalResponse object with the result of the operation.
     * @throws InventoryException if the batch or product inventory is not found, or if the batch does not have enough quantity.
     */
    @Override
    public Mono<UniversalResponse> sellProduct(String stockCode,int quantity) {
        return  Mono.fromCallable(()-> {
            //retrieve batch based on stockCode inform of a list
            Optional<Batch>batchOptional = batchRepository.findByStockCode(stockCode);
            Batch batch = batchOptional.orElseThrow(() -> new InventoryException("Batch not found"));
            Optional<ProductInventory> productInventoryOptional = inventoryRepository.findByProduct(batch.getProduct());
            if (productInventoryOptional.isEmpty()) {
                throw new InventoryException("Product inventory not found");
            }
            ProductInventory productInventory = productInventoryOptional.get();
            batch.setQuantity(batch.getQuantity() - quantity);
            productInventory.updateTotalQuantity();
            batchRepository.save(batch);
            inventoryRepository.save(productInventory);
            //retrieve product inventory from the batch
            return UniversalResponse.builder()
                    .data(productInventory)
                    .message("Product sold successfully")
                    .status(200)
                    .build();
        });
    }

    @Override
    public Mono<UniversalResponse> updateOnProductExpiry() {
        return  Mono.fromCallable(()->{
            List<Product> products= productRepository.findAll();
            LocalDate currentDate = LocalDate.now();
             List<ProductExpiryDetails>products1=  products.stream()
                    .map(product -> {
                        List<BatchExpiryDifference> batchDifferences = batchRepository.findByProduct(product).stream()
                                .map(batch -> {
                                    LocalDate expiryDate = batch.getExpiryDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                                    Period period = Period.between(currentDate, expiryDate);
                                    return new BatchExpiryDifference(batch.getStockCode(), period.getYears(), period.getMonths(), period.getDays());
                                })
                                .collect(Collectors.toList());
                        return new ProductExpiryDetails(product.getName(), batchDifferences);
                    })
                    .collect(Collectors.toList());
             return  UniversalResponse.builder()
                     .status(200).message("product expiry").data(products1).build();

        }).publishOn(Schedulers.boundedElastic());
 }
    @Override
    public Mono<StockValuationResponse> calculateStockValuation() {
        return Mono.fromCallable(() -> {
            List<Batch> batches = batchRepository.findAll();

            int totalBuyingPrice = batches.stream()
                    .mapToInt(batch -> batch.getBuyingPrice() * batch.getQuantity())
                    .sum();

            int totalSellingPrice = batches.stream()
                    .mapToInt(batch -> batch.getSellingPrice() * batch.getQuantity())
                    .sum();

            int totalProfit = totalSellingPrice - totalBuyingPrice;

            return StockValuationResponse.builder()
                    .totalBuyingPrice(totalBuyingPrice)
                    .totalSellingPrice(totalSellingPrice)
                    .totalProfit(totalProfit)
                    .build();
        }).publishOn(Schedulers.boundedElastic());
    }

}








