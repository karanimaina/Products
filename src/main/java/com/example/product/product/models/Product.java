package com.example.product.product.models;

import com.example.product.category.models.Category;
import com.example.product.commons.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
@OneToOne
private Category category;
@OneToMany (mappedBy = "product")
private List<PriceHistory> priceHistories;
}

