package co.com.ecommerce.infrastructure.outadapter.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.ecommerce.infrastructure.outadapter.persistence.entity.CartDetailEntity;

public interface CartDetailJpaRepository extends JpaRepository<CartDetailEntity, Long> {
  
}
