package co.com.ecommerce.infrastructure.outadapter.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.ecommerce.infrastructure.outadapter.persistence.entity.CouponEntity;

public interface CouponJpaRepository extends JpaRepository<CouponEntity, Long> {
  
}
