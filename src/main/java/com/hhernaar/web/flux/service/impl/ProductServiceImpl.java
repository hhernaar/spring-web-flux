package com.hhernaar.web.flux.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hhernaar.web.flux.document.Product;
import com.hhernaar.web.flux.repository.ProductRepository;
import com.hhernaar.web.flux.service.ProductService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductServiceImpl implements ProductService {

  @Autowired
  private ProductRepository productRepository;

  @Override
  public Flux<Product> findAll() {
    return productRepository.findAll();
  }

  @Override
  public Mono<Product> findById(String id) {
    return productRepository.findById(id);
  }

  @Override
  public Mono<Product> saveUpdate(Product product) {
    return productRepository.save(product);
  }

  @Override
  public Mono<Void> delete(Product product) {
    return productRepository.delete(product);
  }

  @Override
  public Mono<Product> findByName(String name) {
    return productRepository.findByName(name);
  }



}
