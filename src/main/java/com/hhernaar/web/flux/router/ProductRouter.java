package com.hhernaar.web.flux.router;

import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.PUT;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import com.hhernaar.web.flux.handler.ProductHandler;

@Configuration
public class ProductRouter {

  @Bean
  public RouterFunction<ServerResponse> routes(ProductHandler handler) {
    return route(GET("/api/producto/"), handler::list)
        .andRoute(GET("/api/producto/{id}/"), handler::detail)
        .andRoute(POST("/api/producto/"), handler::create)
        .andRoute(PUT("/api/producto/{id}/"), handler::update)
        .andRoute(DELETE("/api/producto/{id}/"), handler::delete);
  }

}
