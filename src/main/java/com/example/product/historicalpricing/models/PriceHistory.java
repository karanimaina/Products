package com.example.product.historicalpricing.models;

import com.example.product.commons.BaseEntity;
import com.example.product.manufacture.models.Manufacturer;
import com.example.product.product.models.Product;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
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