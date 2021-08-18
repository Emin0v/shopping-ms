package com.company.api;

import com.company.dto.category.CategoryResponse;
import com.company.dto.product.ProductResponse;
import com.company.service.CategoryService;
import com.company.service.ProductService;
import com.company.util.ApiPaths;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiPaths.CategoryCtrl.CTRL)
@CrossOrigin
public class CategoryController {

    private final CategoryService categoryService;
    private final ProductService productService;

    @GetMapping
    public Flux<CategoryResponse> getAll(){
        return categoryService.getAll();
    }

    @GetMapping("/{categoryId}")
    public Flux<ProductResponse> getAllByCategoryId(@PathVariable("categoryId") String categoryId) {
        return productService.getAllByCategoryId(categoryId);
    }
}
