package com.company.service.impl;

import com.company.dto.product.ProductResponse;
import com.company.dto.product.ProductSaveRequest;
import com.company.model.MoneyTypes;
import com.company.dto.product.ProductDetailResponse;
import com.company.dto.product.ProductSellerResponse;
import com.company.model.Product;
import com.company.model.ProductImage;
import com.company.model.es.ProductEs;
import com.company.repository.mongo.ProductRepository;
import com.company.service.ProductAmountService;
import com.company.service.ProductDeliveryService;
import com.company.service.ProductEsService;
import com.company.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.stream.Collectors;

import static com.company.model.ProductImage.ImageType.FEATURE;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductDeliveryService productDeliveryService;
    private final ProductAmountService productAmountService;
    private final ProductEsService productEsService;

    @Override
    public Flux<ProductResponse> getAll() {
        return productEsService.findAll().map(this::mapToDto);
    }

    @Override
    public Flux<ProductResponse> getAllByCategoryId(String categoryId) {
        return productEsService.findAllByCategoryId(categoryId).map(this::mapToDto);
    }

    @Override
    public ProductResponse save(ProductSaveRequest request) {
        Product product = Product.builder()
                .active(Boolean.TRUE)
                .code("PR0001")
                .categoryId(request.getCategoryId())
                .companyId(request.getSellerId())
                .description(request.getDescription())
                .features(request.getFeatures())
                .name(request.getName())
                .price(request.getPrice())
                .productImage(request.getImages()
                        .stream()
                        .map(it -> new ProductImage(FEATURE, it)).collect(Collectors.toList()))
                .build();

        product = productRepository.save(product).block();
        return this.mapToDto(productEsService.saveNewProduct(product).block());
    }

    private ProductResponse mapToDto(ProductEs item) {
        if (item == null) {
            return null;
        }

        return ProductResponse.builder()
                // TODO
                .price(item.getPrice().get(MoneyTypes.USD))
                .moneySymbol(MoneyTypes.USD.getSymbol())
                .name(item.getName())
                .features(item.getFeatures())
                .id(item.getId())
                .description(item.getDescription())
                .deliveryIn(productDeliveryService.getDeliveryInfo(item.getId()))
                .categoryId(item.getCategory().getId())
                .available(productAmountService.getByProductId(item.getId()))
                .freeDelivery(productDeliveryService.freeDeliveryCheck(item.getId(), item.getPrice().get("USD"), MoneyTypes.USD))
                .image(item.getImages().get(0))
                .seller(ProductSellerResponse.builder().id(item.getSeller().getId()).name(item.getSeller().getName()).build())
                .build();
    }

    @Override
    public Mono<Long> count() {
        return productRepository.count();
    }

    @Override
    public Mono<ProductDetailResponse> getProductDetail(String id) {
        return this.mapToDto(productEsService.findById(id));
    }

    private Mono<ProductDetailResponse> mapToDto(Mono<ProductEs> product) {
        return product.map( item -> ProductDetailResponse.builder()
                .price(item.getPrice().get("USD"))
                .moneySymbol(MoneyTypes.USD.getSymbol())
                .name(item.getName())
                .features(item.getFeatures())
                .id(item.getId())
                .description(item.getDescription())
                .deliveryIn(productDeliveryService.getDeliveryInfo(item.getId()))
                .categoryId(item.getCategory().getId())
                .available(productAmountService.getByProductId(item.getId()))
                .freeDelivery(productDeliveryService.freeDeliveryCheck(item.getId(), item.getPrice().get("USD"), MoneyTypes.USD))
                .images(item.getImages())
                .seller(ProductSellerResponse.builder().id(item.getSeller().getId()).name(item.getSeller().getName()).build())
                .build());
    }
}
