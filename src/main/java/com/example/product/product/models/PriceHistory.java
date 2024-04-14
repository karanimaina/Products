package com.example.product.product.models;

import com.example.product.commons.BaseEntity;
import jakarta.persistence.*;
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