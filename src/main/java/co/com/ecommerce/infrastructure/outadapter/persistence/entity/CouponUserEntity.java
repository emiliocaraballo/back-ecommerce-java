package co.com.ecommerce.infrastructure.outadapter.persistence.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "coupon_users")
public class CouponUserEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "coupon_id")
  private CouponEntity coupon;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private UserEntity user;

  /* audit */
  @Column(name = "created_at")
  private LocalDateTime createdAt;

  @Column(name = "created_by")
  private Long createdBy;

  @Column(name = "updated_at")
  private LocalDateTime updatedAt;

  @Column(name = "updated_by")
  private Long updatedBy;
  
}
