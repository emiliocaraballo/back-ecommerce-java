package co.com.ecommerce.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Product {
  private Long id;
  private String name;
  private String tradeMark;
  private String description;
  private String image;
  private Double price;
  private int stock;
  private String status;


  public int getStock() {
    if(this.stock < 0) {
      throw new IllegalStateException("La cantidad de productos no puede ser menor a 0");
    }
    return this.stock;
  }
  
}
