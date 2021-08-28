package com.company.startup;

import com.company.client.FileStoreServiceClient;
import com.company.dto.category.CategoryResponse;
import com.company.dto.category.CategorySaveRequest;
import com.company.dto.product.ProductSaveRequest;
import com.company.filestore.service.FileStoreService;
import com.company.model.MoneyTypes;
import com.company.repository.es.ProductEsRepository;
import com.company.service.CategoryService;
import com.company.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

import static java.util.UUID.randomUUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProductDemoData {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final ProductEsRepository productEsRepository;
    private final FileStoreService fileStoreService;


    @EventListener(ApplicationReadyEvent.class)
    public void migrate() {

        Long countOfData = productService.count().block();
        assert countOfData != null;
        if (countOfData.equals(0L)) {

            productEsRepository.deleteAll().block();

            CategoryResponse women = categoryService.save(CategorySaveRequest.builder().name("WOMENS").build());
            CategoryResponse mens = categoryService.save(CategorySaveRequest.builder().name("MENS").build());
            CategoryResponse kids = categoryService.save(CategorySaveRequest.builder().name("KIDS").build());


            IntStream.range(0, 5).forEach(item -> {
                HashMap<MoneyTypes, BigDecimal> price = new HashMap<>() {{
                    put(MoneyTypes.USD, BigDecimal.valueOf((item + 1) * 5));
                    put(MoneyTypes.EUR, BigDecimal.valueOf((item + 1) * 4));
                }};

                String imgUuidM = UUID.randomUUID().toString();
                String imgUuidW = UUID.randomUUID().toString();
                String imgUuidK = UUID.randomUUID().toString();

                byte[] mensFile = null;
                byte[] womensFile = null;
                byte[] kidsFile = null;
                try {
                    mensFile = Files.readAllBytes(ResourceUtils.getFile("classpath:product-images/shirt.jpg").toPath());
                    womensFile = Files.readAllBytes(ResourceUtils.getFile("classpath:product-images/womens.jpg").toPath());
                    kidsFile = Files.readAllBytes(ResourceUtils.getFile("classpath:product-images/kids.jpg").toPath());

                } catch (Exception e) {
                    log.error("File read error : ", e);
                }

                fileStoreService.saveImage(imgUuidM,  new ByteArrayInputStream(mensFile));
                fileStoreService.saveImage(imgUuidW,  new ByteArrayInputStream(womensFile));
                fileStoreService.saveImage(imgUuidK,  new ByteArrayInputStream(kidsFile));


                productService.save(
                        ProductSaveRequest.builder()
                                .sellerId(randomUUID().toString())
                                .id(randomUUID().toString())
                                .description("Product Description " + item)
                                .price(price)
                                .categoryId(mens.getId())
                                .name("Product Name " + item)
                                .features("<li>T-shirt</li> <li>Cotton</li> <li>2 Years Warranty</li>")
                                .images(List.of(imgUuidM))
                                .build());

                productService.save(
                        ProductSaveRequest.builder()
                                .sellerId(randomUUID().toString())
                                .id(randomUUID().toString())
                                .description("Product Description " + item)
                                .price(price)
                                .categoryId(women.getId())
                                .name("Product Name " + item)
                                .features("<li>Cotton</li> <li>2 Years Warranty</li>")
                                .images(List.of(imgUuidW))
                                .build());

                productService.save(
                        ProductSaveRequest.builder()
                                .sellerId(randomUUID().toString())
                                .id(randomUUID().toString())
                                .description("Product Description " + item)
                                .price(price)
                                .categoryId(kids.getId())
                                .name("Product Name " + item)
                                .features("<li>Cotton</li> <li>2 Years Warranty</li>")
                                .images(List.of(imgUuidK))
                                .build());

            });
        }
    }

}

