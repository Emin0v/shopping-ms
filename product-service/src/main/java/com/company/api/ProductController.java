package com.company.api;

import com.company.dto.product.ProductDetailResponse;
import com.company.dto.product.ProductPriceResDto;
import com.company.dto.product.ProductResponse;
import com.company.dto.product.ProductSaveRequest;
import com.company.service.ProductEsService;
import com.company.service.ProductService;
import com.company.util.ApiPaths;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiPaths.ProductCtrl.CTRL)
@CrossOrigin
public class ProductController {

    private final ProductService productService;
    private final ProductEsService productEsService;

    @GetMapping
    public Flux<ProductResponse> getAllProducts() {
        return productService.getAll();
    }

    @GetMapping("/{id}")
    public Mono<ProductDetailResponse> getProductDetail(@PathVariable("id") String id) {
        return productService.getProductDetail(id);
    }

    @PostMapping
    public ResponseEntity<ProductResponse> save(ProductSaveRequest productSaveRequest){
        return ResponseEntity.ok(productService.save(productSaveRequest));
    }

    @PostMapping("/price")
    public ResponseEntity<Set<ProductPriceResDto>> getProductPrice(@RequestBody Set<String> productUuids) {
        return ResponseEntity.ok(productEsService.getProductPrice(productUuids));
    }


}
