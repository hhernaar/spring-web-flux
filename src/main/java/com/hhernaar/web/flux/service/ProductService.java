package com.hhernaar.web.flux.service;

import com.hhernaar.web.flux.document.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Product Service: Class to manage product opetrations.
 */
public interface ProductService {

  /**
   * List all products in DB
   * 
   * @return List of products
   * @author hhernaar
   */
  public Flux<Product> findAll();


  /**
   * Find product by it's id
   *
   * @param id The product id
   * @return product
   * @author hhernaar
   */
  public Mono<Product> findById(String id);


  /**
   * Save or Update product in DB
   * 
   * @param product The {@link Product} to save
   * @return The new element.
   * @author hhernaar
   */
  public Mono<Product> saveUpdate(Product product);


  /**
   * Delete product
   * 
   * @param product The {@link Product} to delete
   * @author hhernaar
   */
  public Mono<Void> delete(Product product);


  /**
   * Find product by it's name
   *
   * @param name The product name
   * @return product
   * @author hhernaar
   */
  public Mono<Product> findByName(String name);

}
