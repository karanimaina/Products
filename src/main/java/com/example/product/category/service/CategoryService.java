package com.example.product.category.service;

import com.example.product.category.records.CategoryRecord;
import com.example.product.commons.UniversalResponse;
import com.example.product.product.records.CustomPage;
import java.util.function.Function;

public interface CategoryService {
	Function<CategoryRecord, UniversalResponse> createCategory();
	Function<Long, UniversalResponse>deleteCategory();
	Function<CustomPage,UniversalResponse> viewAlCategory ();
}
