package com.example.product.inventory.records;

import java.util.List;

public record ProductExpiryDetails (
     String productName,
     List<BatchExpiryDifference> batchExpiryDifferences){
}