package com.hhernaar.web.flux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.hhernaar.*")
@SpringBootApplication
public class WebFluxApplication {

  public static void main(String[] args) {
    SpringApplication.run(WebFluxApplication.class, args);
  }

}
