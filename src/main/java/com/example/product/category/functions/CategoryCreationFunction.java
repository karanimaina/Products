package com.example.product.category.functions;


import com.example.product.category.records.CategoryRecord;
import com.example.product.commons.UniversalResponse;

@FunctionalInterface
public interface CategoryCreationFunction {
UniversalResponse apply(CategoryRecord category);
}
