package com.example.product.inventory.records;

import java.util.Date;

public record Inventory(Long productId, Integer  quantity, Date expiryDate,String weight,String barcode, int buying, int selling) {


}
