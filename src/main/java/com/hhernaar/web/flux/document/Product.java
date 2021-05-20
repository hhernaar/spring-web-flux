package com.hhernaar.web.flux.document;

import java.util.Date;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Document(collection = "productos")
public class Product {

  @Id
  private String id;

  @NotEmpty
  private String name;

  @NotNull
  private Double price;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date createAt;

  public Product(String name, Double price) {
    this.name = name;
    this.price = price;
  }

}
