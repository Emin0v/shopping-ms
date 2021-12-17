package com.company.api;

import com.company.dto.category.CategoryResponse;
import com.company.dto.category.CategorySaveRequest;
import com.company.dto.product.ProductResponse;
import com.company.service.CategoryService;
import com.company.service.ProductService;
import com.company.util.ApiPaths;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping(ApiPaths.CategoryCtrl.CTRL)
@CrossOrigin("*")
public class CategoryController {

    private final CategoryService categoryService;
    private final ProductService productService;


    public CategoryController(CategoryService categoryService,
                              ProductService productService){
        this.categoryService=categoryService;
        this.productService= productService;
    }

    @GetMapping
    public Flux<CategoryResponse> getAll(){
        return categoryService.getAll();
    }

    @GetMapping("/{categoryId}")
    public Flux<ProductResponse> getAllByCategoryId(@PathVariable("categoryId") String categoryId) {
        return productService.getAllByCategoryId(categoryId);
    }

    @PostMapping
    public ResponseEntity<CategoryResponse> save(CategorySaveRequest saveRequest){
        return ResponseEntity.ok(categoryService.save(saveRequest));
    }
}
