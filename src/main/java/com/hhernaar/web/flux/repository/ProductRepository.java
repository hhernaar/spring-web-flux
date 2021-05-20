package com.hhernaar.web.flux.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import com.hhernaar.web.flux.document.Product;
import reactor.core.publisher.Mono;


public interface ProductRepository extends ReactiveMongoRepository<Product, String> {

  public Mono<Product> findByName(String name);

}
