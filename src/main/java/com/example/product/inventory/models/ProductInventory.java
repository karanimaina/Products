package com.example.product.inventory.models;

import com.example.product.commons.BaseEntity;
import com.example.product.product.models.Product;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProductInventory extends BaseEntity {
    private Product product;
    private  Batch batch;
}
