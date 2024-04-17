package com.example.product.manufacture.models;

import com.example.product.commons.BaseEntity;
import com.example.product.historicalpricing.models.PriceHistory;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Manufacturer  extends BaseEntity {
private String name;
private String location;
@OneToMany (mappedBy = "manufacturer")
private List<PriceHistory> priceHistories;
private  String phoneNumber;
}
