package co.com.ecommerce.application.business.interfaces;

import java.util.List;

import co.com.ecommerce.application.dto.request.ProductSearchDto;
import co.com.ecommerce.application.dto.response.ProductSalesDTO;
import co.com.ecommerce.domain.model.Product;

public interface ProductBusiness {
    public Product createProduct(Product product);
    public Product findById(Long id);
    public Product updatedProduct(Product product);
    public List<Product> findAll(ProductSearchDto search);
    public List<Product> findTopMostRecent();
    public List<ProductSalesDTO> findTopMostSold();
}
