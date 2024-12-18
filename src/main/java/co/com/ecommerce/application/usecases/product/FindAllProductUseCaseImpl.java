package co.com.ecommerce.application.usecases.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.ecommerce.application.dto.request.ProductSearchDto;
import co.com.ecommerce.domain.model.Product;
import co.com.ecommerce.domain.port.inound.product.FindAllProductUseCase;
import co.com.ecommerce.domain.port.outound.ProductRepositoryPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class FindAllProductUseCaseImpl implements FindAllProductUseCase {
  
  private final ProductRepositoryPort productPort;
  
  @Override
  public List<Product> findAll(ProductSearchDto search) {
    return productPort.findAll(search);
  }
  
}
