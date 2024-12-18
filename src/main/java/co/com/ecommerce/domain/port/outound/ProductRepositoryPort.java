package co.com.ecommerce.domain.port.outound;

import java.util.List;
import java.util.Optional;

import co.com.ecommerce.application.dto.request.ProductSearchDto;
import co.com.ecommerce.application.dto.response.ProductSalesDTO;
import co.com.ecommerce.domain.model.Product;

public interface ProductRepositoryPort {
  public Product createProduct(Product product);
  public List<Product> searchProducts();
  public Optional<Product> findById(Long id);
  public Product updated(Product product);
  public List<Product> findAll(ProductSearchDto search);
  public List<Product> findTopMostRecent();
  public List<ProductSalesDTO> findTopMostSold();
}
