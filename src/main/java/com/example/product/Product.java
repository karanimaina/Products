package com.example.product;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Product extends BaseEntity{
private String name;
private String description;
private String quantity;
private Date expiryDate;
private Date purchaseDate;
private int price;
private Category category;

}

