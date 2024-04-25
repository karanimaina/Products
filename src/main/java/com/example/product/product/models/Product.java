package com.example.product.product.models;

import com.example.product.category.models.Category;
import com.example.product.commons.BaseEntity;
import com.example.product.historicalpricing.models.PriceHistory;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Product extends BaseEntity {
private String name;
private String company;
private String description;
@ManyToOne
@JoinColumn(name = "category")
private Category category;
@OneToMany (mappedBy = "product")
private List<PriceHistory> priceHistories;
}

