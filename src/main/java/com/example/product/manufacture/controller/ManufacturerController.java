package com.example.product.manufacture.controller;

import com.example.product.commons.UniversalResponse;
import com.example.product.manufacture.record.ManufacturerInfo;
import com.example.product.manufacture.service.ManufacturerService;
import com.example.product.product.records.CustomPage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/manufacturer")
public class ManufacturerController {
private  final ManufacturerService manufacturerService;
@PostMapping("/add")
ResponseEntity<UniversalResponse>addManufacturer(@RequestBody ManufacturerInfo manufacturerInfo){
	return  ResponseEntity.ok (manufacturerService.createManufacturer ().apply (manufacturerInfo));
}
@PostMapping("/add/{id}")
ResponseEntity<UniversalResponse>updateManufacturer(@RequestBody ManufacturerInfo manufacturerInfo, @PathVariable("id") Long id){
	return  ResponseEntity.ok (manufacturerService.updateManufacturer ().apply (id,manufacturerInfo));
}
@PostMapping("/delete/{id}")
ResponseEntity<UniversalResponse>deleteManufacturer(@PathVariable("id") Long id){
	return  ResponseEntity.ok (manufacturerService.deleteManufacturer ().apply (id));
}
@PostMapping("/all")
ResponseEntity<UniversalResponse>viewManufacturers(@RequestBody CustomPage customPage){
	return  ResponseEntity.ok (manufacturerService.viewManufacturer ().apply (customPage));
}
@PostMapping("/view/{id}")
ResponseEntity<UniversalResponse>viewManufacturer(@PathVariable ("id")Long  id){
	return  ResponseEntity.ok (manufacturerService.fetchById ().apply (id));
}
}
