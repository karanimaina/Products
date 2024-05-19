package com.example.product.inventory.models;

import com.example.product.commons.BaseEntity;
import com.example.product.product.models.Product;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Batch extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    private Date purchaseDate;
    private Date expiryDate;
    private String weight;
    private Integer quantity;
    private String stockCode;
    private int buyingPrice;
    private int sellingPrice;
}
