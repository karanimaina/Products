package com.example.product;

import jakarta.persistence.Entity;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
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
public class PriceHistory extends BaseEntity {
private String productName;
private String manufacturer;
@Temporal (TemporalType.DATE)
private Date changeDate;
private double price;

}