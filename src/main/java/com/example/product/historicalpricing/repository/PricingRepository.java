package com.example.product.historicalpricing.repository;

import com.example.product.historicalpricing.models.PriceHistory;
import com.example.product.manufacture.models.Manufacturer;
import com.example.product.product.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

public interface PricingRepository  extends JpaRepository<PriceHistory,Long> {
	List<PriceHistory>findByManufacturerAndProduct(Manufacturer manufacturer, Product product);

    boolean existsByManufacturerAndProduct (Manufacturer manufacturer, Product product);
	PriceHistory findByManufacturerAndProductAndAndChangeDate(Manufacturer manufacturer, Product produc, Date date);
}
