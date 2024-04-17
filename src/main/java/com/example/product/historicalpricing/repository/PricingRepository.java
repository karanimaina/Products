package com.example.product.historicalpricing.repository;

import com.example.product.historicalpricing.models.PriceHistory;
import com.example.product.manufacture.models.Manufacturer;
import com.example.product.product.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

public interface PricingRepository  extends JpaRepository<PriceHistory,Long> {
    Stream<PriceHistory>findPriceHistoriesByProduct(Product product);
    boolean existsByManufacturerAndProduct (Manufacturer manufacturer, Product product);
	PriceHistory findByManufacturerAndProductAndAndChangeDate(Manufacturer manufacturer, Product produc, Date date);
}
