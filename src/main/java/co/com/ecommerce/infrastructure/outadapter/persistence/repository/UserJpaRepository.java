package co.com.ecommerce.infrastructure.outadapter.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import co.com.ecommerce.infrastructure.outadapter.persistence.dto.FrequentCustomersTopProjection;
import co.com.ecommerce.infrastructure.outadapter.persistence.entity.UserEntity;
import co.com.ecommerce.infrastructure.outadapter.persistence.entity.UserEntity.RolStatus;

public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {

  public UserEntity findByUsernameAndPasswordAndRol(String username, String password, RolStatus rol);
  
  public UserEntity findByUsername(String username);

    @Query(value = """
      SELECT uuser.id id,person.name name, person.last_name lastName, COUNT(orders.id) totalSales, SUM(orders.total_amount) totalAmount
      FROM orders orders
      INNER JOIN users uuser ON orders.user_id = uuser.id
      INNER JOIN persons person ON uuser.person_id = person.id
      GROUP BY person.name, person.last_name, uuser.id
      ORDER BY SUM(orders.total_amount) DESC
      LIMIT :top
      """, nativeQuery = true)
  List<FrequentCustomersTopProjection> frequentCustomersTop(@Param("top") Integer top);
}
