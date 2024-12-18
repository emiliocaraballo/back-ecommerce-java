package co.com.ecommerce.infrastructure.outadapter.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import co.com.ecommerce.infrastructure.outadapter.persistence.entity.ProductEntity;

public interface ProductJpaRepository extends JpaRepository<ProductEntity, Long> {

  @Query(value = """
         SELECT p.* FROM products p
          WHERE (:name = '' OR p.name ILIKE :name) 
         AND (:trademark = '' OR p.trademark ILIKE :trademark)
         AND (:status IS NULL OR p.status = :status);
     
      """, nativeQuery = true)
  public List<ProductEntity> findByNameAndTradeMarkAndStatus(@Param("name") String name, @Param("trademark") String tradeMark, @Param("status") String status);

  @Query(value = """
          SELECT p.*
          FROM products p
          WHERE (p.status = 'ACTIVE')
          ORDER BY p.created_at DESC
          LIMIT :top
      """, nativeQuery = true)
  public List<ProductEntity> findTopMostRecent(Integer top);
}
