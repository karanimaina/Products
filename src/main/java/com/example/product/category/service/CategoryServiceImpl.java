package com.example.product.category.service;

import com.example.product.category.exceptions.CategoryExceptions;
import com.example.product.category.models.Category;
import com.example.product.category.records.CategoryRecord;
import com.example.product.category.repository.CategoryRepository;
import com.example.product.commons.UniversalResponse;
import com.example.product.product.exceptions.ProductException;
import com.example.product.product.records.CustomPage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Function;
@RequiredArgsConstructor
@Service
public class CategoryServiceImpl  implements CategoryService{
private final CategoryRepository categoryRepository;
@Override
public Function<CategoryRecord, UniversalResponse> createCategory () {
		return  category -> {
			Optional<Category> retrievedCategory = categoryRepository.findByName (category.name ());
			if (retrievedCategory.isPresent()) {
				throw  new ProductException ("product already exist");
			}
			Category newCategory = Category
					                       .builder ()
					                       .name (category.name ())
					                       .build ();
					                       
			categoryRepository.save (newCategory);
			return UniversalResponse.builder ()
					       .status (200)
					       .message ("category created  successfully")
					       .data (category)
					       .build ();
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
public Function<CustomPage, UniversalResponse> viewAlCategory () {
	return  customPage -> {
		Pageable pageable = PageRequest.of (customPage.pageNumber (),  customPage.pageSize ());
		return UniversalResponse.builder ()
				       .data (categoryRepository.findAll (pageable))
				       .status (200)
				       .message ("category retrieved successfully")
				       .build ();
	} ;
}
}

