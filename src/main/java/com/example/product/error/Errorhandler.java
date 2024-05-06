package com.example.product.error;

import com.example.product.category.exceptions.CategoryExceptions;
import com.example.product.commons.UniversalResponse;
import com.example.product.historicalpricing.exceptions.PriceException;
import com.example.product.manufacture.exceptions.ManufacturerException;
import com.example.product.product.exceptions.ProductException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.xml.catalog.CatalogException;

@ControllerAdvice
public class Errorhandler {
	@ExceptionHandler({CategoryExceptions.class, CatalogException.class, ProductException.class, PriceException.class, ManufacturerException.class})
	public UniversalResponse tokenRefreshExceptionErrorHandler(Exception e){
		return UniversalResponse
				       .builder()
				       .message(e.getLocalizedMessage())
				       .build();
		
	}

}
