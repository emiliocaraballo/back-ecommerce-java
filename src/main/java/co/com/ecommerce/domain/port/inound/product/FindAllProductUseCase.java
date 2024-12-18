package co.com.ecommerce.domain.port.inound.product;

import java.util.List;

import co.com.ecommerce.application.dto.request.ProductSearchDto;
import co.com.ecommerce.domain.model.Product;

public interface FindAllProductUseCase {
  public List<Product> findAll(ProductSearchDto search);
}
