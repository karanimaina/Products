package com.example.product.historicalpricing.service;

import com.example.product.commons.UniversalResponse;
import com.example.product.historicalpricing.exceptions.PriceException;
import com.example.product.historicalpricing.models.PriceHistory;
import com.example.product.historicalpricing.records.PricingData;
import com.example.product.historicalpricing.repository.PricingRepository;
import com.example.product.manufacture.models.Manufacturer;
import com.example.product.manufacture.repositories.ManufacturerRepository;
import com.example.product.product.models.Product;
import com.example.product.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class PricingServiceImpl  implements PricingService{
private final PricingRepository pricingRepository;
private  final ManufacturerRepository manufacturerRepository;
private  final ProductRepository productRepository;
@Override
public Mono<Function<PricingData, UniversalResponse>> addPricing () {
	return Mono.fromCallable (()->
	 pricingData -> {
		Optional<Manufacturer>manufacturer = manufacturerRepository.findById (pricingData.manufacturerId ());
		Optional<Product>product = productRepository.findById (pricingData.productId ());
		if (product.isPresent() && manufacturer.isPresent ()) {
			boolean existsInPriceHistory = pricingRepository.existsByManufacturerAndProduct(manufacturer.get(), product.get());
			if (existsInPriceHistory){
				throw new RuntimeException ("manufacturer with the given product already exists");
			}
			PriceHistory priceHistory= PriceHistory.builder ()
					                           .product (product.get ())
					                           .manufacturer (manufacturer.get ())
					                           .price (pricingData.price())
					                           .changeDate (pricingData.changeDate())
					                           .build ();
			pricingRepository.save (priceHistory);
			return UniversalResponse
					       .builder ()
					       .data (priceHistory)
					       .message ("pricing history added successfully")
					       .status (200)
					       .build ();
			
			
			
			
		}
		throw  new PriceException ("THe price history already exists");
	});
}

@Override
public Mono<BiFunction<PricingData, Date ,UniversalResponse>>deletePricing () {
	return Mono.fromCallable (()-> (pricingData, date) -> {
			Optional<Manufacturer>manufacturer = manufacturerRepository.findById (pricingData.manufacturerId ());
			Optional<Product>product = productRepository.findById (pricingData.productId ());
			if (product.isEmpty () && manufacturer.isEmpty ()) {
			    throw  new PriceException ("provided product or manufacturer do  not exist");
				}
		PriceHistory  pricingHistory =pricingRepository.findByManufacturerAndProductAndAndChangeDate (manufacturer.get(), product.get(), date);
		pricingRepository.delete (pricingHistory);
		return UniversalResponse
				       .builder ()
				       .status (200)
				       .message ("pricing details deleted successfully")
				       .build ();
	});
}

@Override
public Mono<Function<Long,UniversalResponse>> retrievePriceHistoryForAProduct () {
	return Mono.fromCallable (()-> productId  ->{
			Optional<Product> product = productRepository.findById (productId);
			if (product.isPresent()){
				Stream<PriceHistory> priceHistory = pricingRepository.findPriceHistoriesByProduct (product.get ());
				return UniversalResponse.builder()
						       .data (priceHistory.collect (Collectors.toList ()))
						       .status (200)
						       .message ("price history retrieved successfully")
						       .build();
				
			}
			return UniversalResponse.builder()
					       .message ("product does not have a  price history")
					       .status (200)
					       .data (new ArrayList<> ())
					       .build();
	});
	
}
}
