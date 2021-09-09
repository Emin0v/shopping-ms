package com.company.repository.es;

import com.company.model.es.ProductEs;
import org.springframework.data.elasticsearch.repository.ReactiveElasticsearchRepository;
import reactor.core.publisher.Flux;

import java.util.List;

public interface ProductEsRepository extends ReactiveElasticsearchRepository<ProductEs, String> {

    Flux<ProductEs> findAllByCategory_Id(String id);

    Flux<ProductEs> findAllByIdIn(List<String> productUuids);
}
