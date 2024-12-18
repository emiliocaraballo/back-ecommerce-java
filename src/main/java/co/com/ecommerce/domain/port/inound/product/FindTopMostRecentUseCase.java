package co.com.ecommerce.domain.port.inound.product;

import java.util.List;

import co.com.ecommerce.domain.model.Product;

public interface FindTopMostRecentUseCase {
   public List<Product> findTopMostRecent();
}
