package com.example.product.historicalpricing.controller;

import com.example.product.commons.UniversalResponse;
import com.example.product.historicalpricing.records.PricingData;
import com.example.product.historicalpricing.service.PricingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/price")
public class PriceController {
private  final PricingService pricingService;
@PostMapping("/add")
ResponseEntity<UniversalResponse>addPrice(@RequestBody PricingData pricingData ){
return  ResponseEntity.ok (pricingService.addPricing ().apply (pricingData));
}
@PostMapping("/delete")
ResponseEntity<UniversalResponse>deletePrice(@RequestBody PricingData pricingData, @RequestParam("date") Date date){
	return  ResponseEntity.ok (pricingService.deletePricing ().apply (pricingData,date));
}
@PostMapping("/retrieve/{id}")
ResponseEntity<UniversalResponse>retrievePrice(@PathVariable(name = "id") Long id){
	return  ResponseEntity.ok (pricingService.retrievePriceHistoryForAProduct ().apply (id));
}
}
