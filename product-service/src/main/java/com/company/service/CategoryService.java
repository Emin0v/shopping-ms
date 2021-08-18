package com.company.service;

import com.company.dto.category.CategoryResponse;
import com.company.dto.category.CategorySaveRequest;
import com.company.model.category.Category;
import reactor.core.publisher.Flux;

public interface CategoryService {

    Flux<CategoryResponse> getAll();

    Category getById(String id);

    CategoryResponse save(CategorySaveRequest request);

}
