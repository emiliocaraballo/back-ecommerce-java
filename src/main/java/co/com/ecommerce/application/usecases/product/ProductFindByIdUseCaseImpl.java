package co.com.ecommerce.application.usecases.product;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.ecommerce.application.exception.CustomException;
import co.com.ecommerce.domain.model.Product;
import co.com.ecommerce.domain.port.inound.product.ProductFindByIdUseCase;
import co.com.ecommerce.domain.port.outound.ProductRepositoryPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ProductFindByIdUseCaseImpl implements ProductFindByIdUseCase {

  private final ProductRepositoryPort productPort;

  @Override
  public Product findById(Long id) {
    Optional<Product> product = productPort.findById(id);
    if (product.isEmpty()) {
      throw new CustomException("PRODUCT_NOT_FOUND", 404);
    }
    return productPort.findById(id).get();
  }
  
}
