package co.com.ecommerce.infrastructure.outadapter.persistence.adapter;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.com.ecommerce.application.dto.request.ProductSearchDto;
import co.com.ecommerce.application.dto.response.ProductSalesDTO;
import co.com.ecommerce.domain.model.Product;
import co.com.ecommerce.domain.port.outound.ProductRepositoryPort;
import co.com.ecommerce.infrastructure.outadapter.persistence.dto.FindTopMostSoldProductsProjection;
import co.com.ecommerce.infrastructure.outadapter.persistence.entity.ProductEntity;
import co.com.ecommerce.infrastructure.outadapter.persistence.mapper.ProductMapper;
import co.com.ecommerce.infrastructure.outadapter.persistence.repository.OrderItemJpaRepository;
import co.com.ecommerce.infrastructure.outadapter.persistence.repository.ProductJpaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ProductRepositoryAdapter implements ProductRepositoryPort {
  private final ProductJpaRepository jpaRepository;
  private final OrderItemJpaRepository orderItemJpaRepository;
  private final ProductMapper productMapper;

  @Override
  @Transactional
  public Product createProduct(Product product) {
    ProductEntity entity = productMapper.toEntity(product);
    ProductEntity savedEntity = jpaRepository.save(entity);
    return productMapper.toDomain(savedEntity);
  }

  @Override
  public List<Product> searchProducts() {
    throw new UnsupportedOperationException("Unimplemented method 'searchProducts'");
  }

  @Override
  public Optional<Product> findById(Long id) {
    return jpaRepository.findById(id)
    .map(productMapper::toDomain);
  }

  @Override
  @Transactional
  public Product updated(Product product) {
    ProductEntity entity = productMapper.toEntity(product);

    log.info("update product: {}", product);

    ProductEntity result = jpaRepository.findById(product.getId()).orElse(null);
   
    ProductEntity savedEntity = jpaRepository.save(productMapper.merge(entity, result));
    return productMapper.toDomain(savedEntity);
  }

  @Override
  public List<Product> findAll(ProductSearchDto search) {
    String statusValue = null;
    if(search.getStatus() != null) {
      statusValue = search.getStatus();
    }

    String nameValue = "";
    if(search.getName() != null) {
      nameValue = "%" +search.getName() + "%";
    }

    String tradeMarkValue = "";
    if(search.getTradeMark() != null) {
      tradeMarkValue = "%" + search.getTradeMark() + "%";
    }
    
    log.info("search name: {}, tradeMark: {}, status: {}", nameValue, tradeMarkValue, statusValue);
    List<ProductEntity> entities = jpaRepository.findByNameAndTradeMarkAndStatus(nameValue, tradeMarkValue, statusValue);
    return entities.stream().map(productMapper::toDomain).toList();
  }

  @Override
  public List<Product> findTopMostRecent() {
    List<ProductEntity> entities = jpaRepository.findTopMostRecent(5);
    return entities.stream().map(productMapper::toDomain).toList();
  }

  @Override
  public List<ProductSalesDTO> findTopMostSold() {
    List<FindTopMostSoldProductsProjection> entities = orderItemJpaRepository.findTopMostSoldProducts(5);
    return entities.stream().map(projection -> new ProductSalesDTO(projection.getId(), projection.getName(), projection.getTotalSales(), projection.getImage())).toList();
  }
}
