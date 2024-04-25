package com.example.product.category.service;

import com.example.product.category.exceptions.CategoryExceptions;
import com.example.product.category.functions.CategoryCreationFunction;
import com.example.product.category.models.Category;
import com.example.product.category.records.CategoryRecord;
import com.example.product.category.repository.CategoryRepository;
import com.example.product.commons.UniversalResponse;
import com.example.product.product.exceptions.ProductException;
import com.example.product.product.models.Product;
import com.example.product.product.records.CustomPage;
import com.example.product.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;
@RequiredArgsConstructor
@Service
public class CategoryServiceImpl  implements CategoryService{
private final CategoryRepository categoryRepository;
private final ProductRepository productRepository;
@Override
public Function<CategoryRecord, UniversalResponse> createCategory() {
	return category -> {
			Optional<Category> retrievedCategory = categoryRepository.findByName(category.name());
			if (retrievedCategory.isPresent()) {
				throw new ProductException("Product already exists");
			}
			Category newCategory = Category.builder()
					                       .name(category.name())
					                       .build();
			
			categoryRepository.save(newCategory);
			return UniversalResponse.builder()
					       .status(200)
					       .message("Category created successfully")
					       .data(category)
					       .build();
		};
}

@Override
public Function<Long, UniversalResponse> deleteCategory () {
		return longId->{
			Optional<Category> category = categoryRepository.findById (longId);
			if (category.isEmpty ()) {
				throw  new CategoryExceptions ("Category not found");
			}
			categoryRepository.delete(category.get ());
			return UniversalResponse.builder ()
					       .status (200)
					       .message ("category deleted successfully")
					       .build ();
		};
	}



@Override
public Function<CustomPage, UniversalResponse> viewAllCategory () {
	return  customPage -> {
		Pageable pageable = PageRequest.of (customPage.pageNumber (),  customPage.pageSize ());
		return UniversalResponse.builder ()
				       .data (categoryRepository.findAll (pageable))
				       .status (200)
				       .message ("category retrieved successfully")
				       .build ();
	} ;
}

@Override
public BiFunction<Long, Long, UniversalResponse> addProductsToCategory () {
	return (productId,categoryId)->{
		Optional<Category> category = categoryRepository.findById (categoryId);
		Optional<Product> product = productRepository.findById (categoryId);
		if (product. isPresent () && category.isPresent ())
		{
			Product retrieveProduct= product.get ();
			retrieveProduct.setCategory (category.get ());
			productRepository.save (retrieveProduct);
			return  UniversalResponse.builder ()
					        .message ("product added to categpry")
					        .status (200)
					        .data (retrieveProduct)
					        .build ();
		}
		throw  new ProductException ("product / category mpt fpund");
	};

}
}

