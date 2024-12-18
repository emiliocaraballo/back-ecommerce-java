package co.com.ecommerce.application.business.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.ecommerce.application.business.interfaces.ProductBusiness;
import co.com.ecommerce.application.dto.request.ProductSearchDto;
import co.com.ecommerce.application.dto.response.ProductSalesDTO;
import co.com.ecommerce.application.exception.CustomException;
import co.com.ecommerce.domain.model.Product;
import co.com.ecommerce.domain.port.inound.product.FindAllProductUseCase;
import co.com.ecommerce.domain.port.inound.product.FindTopMostRecentUseCase;
import co.com.ecommerce.domain.port.inound.product.FindTopMostSoldUseCase;
import co.com.ecommerce.domain.port.inound.product.ProductCreateUseCase;
import co.com.ecommerce.domain.port.inound.product.ProductFindByIdUseCase;
import co.com.ecommerce.domain.port.inound.product.ProductUpdatedUseCase;
import co.com.ecommerce.domain.shared.Either;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ProductImpl implements ProductBusiness, ProductCreateUseCase, ProductFindByIdUseCase,
    ProductUpdatedUseCase, FindAllProductUseCase, FindTopMostRecentUseCase, FindTopMostSoldUseCase {

  private final ProductCreateUseCase createProductUseCase;
  private final ProductFindByIdUseCase productFindByIdUseCase;
  private final ProductUpdatedUseCase productUpdatedUseCase;
  private final FindAllProductUseCase findAllProductUseCase;
  private final FindTopMostRecentUseCase findTopMostRecentUseCase;
  private final FindTopMostSoldUseCase findTopMostSoldUseCase;

  @Override
  public Product createProduct(Product product) {
    Product response = createProductUseCase.createProduct(product);
    log.info("end process: Creating product: {}", response);
    return response;
  }

  @Override
  public Product findById(Long id) {
    Product response = productFindByIdUseCase.findById(id);
    log.info("end process: Finding product by id: {}", response);
    return response;
  }

  @Override
  public Product updatedProduct(Product product) {
    Either<Product, Object> result = Either.run(() -> productFindByIdUseCase.findById(product.getId()), e -> e);
    if (result.isRight()) {
      log.error("end process: Product not found");
      throw new CustomException("PRODUCT_NOT_FOUND", 404);
    }
    Product response = productUpdatedUseCase.updatedProduct(product);
    log.info("end process: Updating product: {}", response);
    return response;
  }

  @Override
  public List<Product> findAll(ProductSearchDto search) {
    List<Product> response = findAllProductUseCase.findAll(search);
    log.info("end process: Finding all products: {}", response);
    return response;
  }

  @Override
  public List<Product> findTopMostRecent() {
    List<Product> response = findTopMostRecentUseCase.findTopMostRecent();
    log.info("end process: Finding top most recent products: {}", response);
    return response;
  }

  @Override
  public List<ProductSalesDTO> findTopMostSold() {
    List<ProductSalesDTO> response = findTopMostSoldUseCase.findTopMostSold();
    log.info("end process: Finding top most sold products: {}", response);
    return response;
  }
}
