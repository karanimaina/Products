package com.example.product.manufacture.repositories;

import com.example.product.manufacture.models.Manufacturer;
import com.example.product.manufacture.record.ManufacturerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.function.LongFunction;

public interface ManufacturerRepository extends JpaRepository<Manufacturer, Long> {
Optional<Manufacturer> findByName (ManufacturerInfo manufacturerInfo);
}
