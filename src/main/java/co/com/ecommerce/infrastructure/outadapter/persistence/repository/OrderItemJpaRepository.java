package co.com.ecommerce.infrastructure.outadapter.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import co.com.ecommerce.infrastructure.outadapter.persistence.dto.FindTopMostSoldProductsProjection;
import co.com.ecommerce.infrastructure.outadapter.persistence.entity.OrderItemEntity;

public interface OrderItemJpaRepository extends JpaRepository<OrderItemEntity, Long> {

  @Query(value = """
       SELECT oi.product_id id, p.name name, SUM(oi.quantity) totalSales, p.image image
      FROM order_items oi
      INNER JOIN products p ON oi.product_id = p.id
      GROUP BY oi.product_id, p.name, p.image
      ORDER BY SUM(oi.quantity) DESC
      LIMIT :top
      """, nativeQuery = true)
  List<FindTopMostSoldProductsProjection> findTopMostSoldProducts(@Param("top") Integer top);
}
