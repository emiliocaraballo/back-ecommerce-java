package co.com.ecommerce.domain.port.inound.product;

import co.com.ecommerce.domain.model.Product;

public interface ProductUpdatedUseCase {
  Product updatedProduct(Product product);
}
