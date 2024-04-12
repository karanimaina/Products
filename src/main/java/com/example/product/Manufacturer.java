package com.example.product;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Manufacturer  extends  BaseEntity{
private String name;
private String location;
@OneToMany (mappedBy = "manufacturer")
private List<PriceHistory> priceHistories;
}
