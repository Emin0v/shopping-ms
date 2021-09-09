package com.company.client;

import com.company.dto.product.ProductPriceResDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Set;

@FeignClient("product-service")
public interface ProductServiceClient {

    @PostMapping("/api/products/price")
    Set<ProductPriceResDto> getProductPrice(@RequestBody Set<String> productUuids);

}
