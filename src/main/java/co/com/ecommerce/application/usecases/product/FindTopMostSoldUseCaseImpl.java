package co.com.ecommerce.application.usecases.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.ecommerce.application.dto.response.ProductSalesDTO;
import co.com.ecommerce.domain.port.inound.product.FindTopMostSoldUseCase;
import co.com.ecommerce.domain.port.outound.ProductRepositoryPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class FindTopMostSoldUseCaseImpl implements FindTopMostSoldUseCase {
  
  private final ProductRepositoryPort productPort;
  
  @Override
  public List<ProductSalesDTO> findTopMostSold() {
    return productPort.findTopMostSold();
  }
  
}
