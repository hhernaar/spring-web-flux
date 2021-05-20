package com.hhernaar.web.flux.handler;

import java.net.URI;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import com.hhernaar.web.flux.document.Product;
import com.hhernaar.web.flux.service.ProductService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class ProductHandler {

  @Autowired
  private ProductService productService;

  @Autowired
  private Validator validator;

  public Mono<ServerResponse> list(ServerRequest request) {
    return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
        .body(productService.findAll(), Product.class);
  }

  public Mono<ServerResponse> detail(ServerRequest request) {
    String id = request.pathVariable("id");
    return productService.findById(id).flatMap(p -> ServerResponse.ok()
        .contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromValue(p)))
        .switchIfEmpty(ServerResponse.notFound().build());
  }

  public Mono<ServerResponse> create(ServerRequest request) {
    Mono<Product> Product = request.bodyToMono(Product.class);
    return Product.flatMap(p -> {

      Errors errors = new BeanPropertyBindingResult(p, Product.class.getName());
      validator.validate(p, errors);
      if (errors.hasErrors()) {
        return Flux.fromIterable(errors.getFieldErrors())
            .map(fieldError -> "El campo " + fieldError.getField() + " "
                + fieldError.getDefaultMessage())
            .collectList()
            .flatMap(le -> ServerResponse.badRequest().body(BodyInserters.fromValue(le)));
      } else {
        p.setCreateAt(new Date());
        return productService.saveUpdate(p)
            .flatMap(pdb -> ServerResponse.created(URI.create("/api/product/".concat(pdb.getId())))
                .contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromValue(pdb)));
      }
    });
  }

  public Mono<ServerResponse> update(ServerRequest request) {
    Mono<Product> Product = request.bodyToMono(Product.class);
    String id = request.pathVariable("id");

    Mono<Product> ProductDB = productService.findById(id);

    return ProductDB.zipWith(Product, (db, req) -> {
      db.setName(req.getName());
      db.setPrice(req.getPrice());
      return db;
    }).flatMap(p -> ServerResponse.created(URI.create("/api/product/".concat(p.getId())))
        .contentType(MediaType.APPLICATION_JSON).body(productService.saveUpdate(p), Product.class))
        .switchIfEmpty(ServerResponse.notFound().build());
  }

  public Mono<ServerResponse> delete(ServerRequest request) {
    String id = request.pathVariable("id");
    Mono<Product> ProductDB = productService.findById(id);

    return ProductDB.flatMap(p -> productService.delete(p).then(ServerResponse.noContent().build()))
        .switchIfEmpty(ServerResponse.notFound().build());
  }

}
