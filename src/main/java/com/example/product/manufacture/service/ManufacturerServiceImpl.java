package com.example.product.manufacture.service;

import com.example.product.commons.UniversalResponse;
import com.example.product.manufacture.exceptions.ManufacturerException;
import com.example.product.manufacture.models.Manufacturer;
import com.example.product.manufacture.record.ManufacturerInfo;
import com.example.product.manufacture.repositories.ManufacturerRepository;
import com.example.product.product.records.CustomPage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;
@Service
@RequiredArgsConstructor
public class ManufacturerServiceImpl implements ManufacturerService  {
private final ManufacturerRepository manufacturerRepository;

@Override
public Function<ManufacturerInfo, UniversalResponse> createManufacturer () {
	return manufacturerInfo -> {
		Optional<Manufacturer> manufacturer = manufacturerRepository.findByName (manufacturerInfo);
		if (manufacturer.isEmpty ()) {
			throw new ManufacturerException ("Manufacturer with id  does not exist");
		}
		Manufacturer manufacturer1 = Manufacturer
				                             .builder ()
				                             .phoneNumber (manufacturerInfo.phone ())
				                             .location (manufacturerInfo.location ())
				                             .build ();
			manufacturerRepository.save (manufacturer1);
		return UniversalResponse.builder ()
				       .status (200)
				       .message (" manufacturer saved  successfully")
				       .build ();
	};
}

@Override
public BiFunction<Long,ManufacturerInfo, UniversalResponse> updateManufacturer () {
	return (id, manufacturerInfo) -> {
		Optional<Manufacturer> manufacturer = manufacturerRepository.findById (id);
		if (manufacturer.isEmpty ()) {
			throw new ManufacturerException ("Manufacturer with id " + id + " does not exist");
		}
		Manufacturer manufacturer1 = manufacturer.get ();
		manufacturer1.setLocation (manufacturerInfo.location ());
		manufacturer1.setName (manufacturerInfo.name ());
		manufacturer1.setPhoneNumber (manufacturerInfo.phone ());
		manufacturerRepository.save (manufacturer1);
		return UniversalResponse.builder ()
				       .status (200)
				       .message (" manufacturer updated successfully")
				       .data (manufacturer1)
				       .build ();
	};
}
@Override
public Function<Long, UniversalResponse> deleteManufacturer () {
	return longId->{
		Optional<Manufacturer> manufacturer = manufacturerRepository.findById (longId);
		if (manufacturer.isEmpty ()) {
			throw  new ManufacturerException ("manufacturer not found");
		}
		manufacturerRepository.delete(manufacturer.get ());
		return UniversalResponse.builder ()
				       .status (200)
				       .message ("manufacturer deleted successfully")
				       .build ();
	};
}

@Override
public Function<CustomPage, UniversalResponse> viewManufacturer () {
	return  customPage -> {
		Pageable pageable = PageRequest.of (customPage.pageNumber (),  customPage.pageSize ());
		return UniversalResponse.builder ()
				       .data (manufacturerRepository.findAll (pageable))
				       .status (200)
				       .message ("manufacturers retrieved successfully")
				       .build ();
	} ;
}

@Override
public Function<Long, UniversalResponse> fetchById () {
	return longId ->{
		Optional<Manufacturer> manufacturer = manufacturerRepository.findById (longId);
		if (manufacturer.isEmpty ()) {
			throw  new ManufacturerException ("Manufacturer not found");
		}
		return UniversalResponse.builder ()
				       .data (manufacturer.get ())
				       .status (200)
				       .message ("Manufacturer retrieved successfully")
				       .build ();
	};
}
}


