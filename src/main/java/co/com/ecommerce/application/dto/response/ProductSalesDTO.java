package co.com.ecommerce.application.dto.response;

import lombok.Data;

@Data
public class ProductSalesDTO {

  private Long id;
  private String name;
  private Long totalSales;
  private String image;

  public ProductSalesDTO(Long id, String name, Long totalSales, String image) {
      this.id = id;
      this.name = name;
      this.totalSales = totalSales;
      this.image = image;
  }
}
