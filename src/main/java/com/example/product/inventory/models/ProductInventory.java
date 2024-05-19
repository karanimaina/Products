package com.example.product.inventory.models;

import com.example.product.commons.BaseEntity;
import com.example.product.product.models.Product;
import jakarta.persistence.*;

import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProductInventory extends BaseEntity {
    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @OneToMany(mappedBy = "inventory", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Batch> batches;
    private Integer totalQuantity;
}
