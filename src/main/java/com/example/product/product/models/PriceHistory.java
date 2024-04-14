package com.example.product.product.models;

import com.example.product.commons.BaseEntity;
import com.example.product.product.models.Manufacturer;
import com.example.product.product.models.Product;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.*;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PriceHistory extends BaseEntity {

	
	@ManyToOne
	@JoinColumn(name = "product_id", nullable = false)
	private Product product;
	
	@ManyToOne
	@JoinColumn(name = "manufacturer_id", nullable = false)
	private Manufacturer manufacturer;
	
	@Temporal(TemporalType.DATE)
	private Date changeDate;
	
	private double price;


}