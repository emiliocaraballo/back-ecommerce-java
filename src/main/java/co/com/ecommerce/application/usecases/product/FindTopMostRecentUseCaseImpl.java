package co.com.ecommerce.application.usecases.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.ecommerce.domain.model.Product;
import co.com.ecommerce.domain.port.inound.product.FindTopMostRecentUseCase;
import co.com.ecommerce.domain.port.outound.ProductRepositoryPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class FindTopMostRecentUseCaseImpl implements FindTopMostRecentUseCase {
 
  private final ProductRepositoryPort productPort;

  @Override
  public List<Product> findTopMostRecent() {
    return productPort.findTopMostRecent();
  }
}
