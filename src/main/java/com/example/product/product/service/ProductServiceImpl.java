package com.example.product.product.service;

import com.example.product.product.models.Category;
import com.example.product.category.CategoryRepository;
import com.example.product.commons.UniversalResponse;
import com.example.product.product.exceptions.ProductException;
import com.example.product.product.models.Product;
import com.example.product.product.records.CustomPage;
import com.example.product.product.records.ProductId;
import com.example.product.product.records.ProductInfo;
import com.example.product.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Function;

@RequiredArgsConstructor
@Slf4j
@Service
public class ProductServiceImpl implements ProductService {
private  final ProductRepository productRepository;
private  final  CategoryRepository categoryRepository;
@Override
public Function<ProductInfo, UniversalResponse> createProduct () {
	return  product -> {
		Optional<Product> retrievedProduct = productRepository.findByNameIgnoreCaseAndCompany (product.name (),product.company ());
		if (retrievedProduct.isPresent()) {
			throw  new ProductException ("product already wxist");
		}
		Category category = new Category();
		category.setName (product.category ());
		categoryRepository.save (category);
		Product createProduct =Product.builder ()
				.company (product.company ())
				.description (product.description ())
				.category (category)
				.name (product.name ())
				.build ();
		productRepository.save (createProduct);
		return UniversalResponse.builder ()
				       .status (200)
				       .message ("product created  successfully")
				       .data (product)
				       .build ();
	};
}

@Override
public Function<ProductId, UniversalResponse> updateProduct () {
	return productId -> {
		Optional<Product> product = productRepository.findById (productId.id ());
		if (product.isEmpty ()) {
			throw new ProductException ("Product with id " + productId + " does not exist");
		}
		Product existingProduct = product.get ();
		existingProduct.setName (productId.name ());
		existingProduct.setCompany (productId.description ());
		Category category = new Category ();
		category.setName (productId.category ());
		existingProduct.setCategory(category);
		productRepository.save (existingProduct);
		return UniversalResponse.builder ()
				       .status (200)
				       .message (" product updated successfully")
				       .data (existingProduct)
				       .build ();
	};
}

@Override
public Function<Long, UniversalResponse> deleteProduct () {
	return longId->{
		Optional<Product> product = productRepository.findById (longId);
		if (product.isEmpty ()) {
			throw  new ProductException ("Product not found");
		}
		productRepository.delete(product.get ());
		return UniversalResponse.builder ()
				       .status (200)
				       .message ("product deleted successfully")
				       .build ();
	};
}

@Override
public Function<CustomPage,UniversalResponse> viewProducts () {
	return  customPage -> {
		Pageable pageable = PageRequest.of (customPage.pageNumber (),  customPage.pageSize ());
		productRepository.findAll (pageable);
		return UniversalResponse.builder ()
				       .data (productRepository.findAll (pageable))
				       .status (200)
				       .message ("product retrieved successfully")
				       .build ();
	} ;
}

@Override
public Function<Long, UniversalResponse> fetchById () {
	return longId ->{
		Optional<Product> product = productRepository.findById (longId);
		if (product.isEmpty ()) {
			throw  new ProductException ("Product not found");
		}
		return UniversalResponse.builder ()
				       .data (product.get ())
				       .status (200)
				       .message ("product retrieved successfully")
				       .build ();
	};
}
}
