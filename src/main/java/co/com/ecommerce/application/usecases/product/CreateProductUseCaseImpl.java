package co.com.ecommerce.application.usecases.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.ecommerce.domain.model.Product;
import co.com.ecommerce.domain.port.inound.product.ProductCreateUseCase;
import co.com.ecommerce.domain.port.outound.ProductRepositoryPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class CreateProductUseCaseImpl implements ProductCreateUseCase {

  private final ProductRepositoryPort productPort;

  @Override
  public Product createProduct(Product product) {
    return productPort.createProduct(product);
  }
}
