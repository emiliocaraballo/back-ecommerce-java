package co.com.ecommerce.domain.port.inound.product;

import java.util.List;

import co.com.ecommerce.application.dto.response.ProductSalesDTO;

public interface FindTopMostSoldUseCase {
  public List<ProductSalesDTO> findTopMostSold();
}
