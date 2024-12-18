package co.com.ecommerce.infrastructure.outadapter.persistence.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "coupons")
public class CouponEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true)
  private String code;

  @Column(nullable = false, name = "discount_percentage")
  private Double discountPercentage;

  @Column(nullable = false, name = "start_date")
  private String startDate;

  @Column(nullable = false, name = "end_date")
  private String endDate;

  @Column(nullable = false, name = "max_limit")
  private Integer limit;

  @Column(nullable = false, name = "user_status")
  private Boolean userStatus;

  @Enumerated(EnumType.STRING)
  private CuoponStatus status;

  /* audit */
  @Column(name = "created_at")
  private LocalDateTime createdAt;

  @Column(name = "created_by")
  private Long createdBy;

  @Column(name = "updated_at")
  private LocalDateTime updatedAt;

  @Column(name = "updated_by")
  private Long updatedBy;

  public enum CuoponStatus {
    ACTIVE, INACTIVE
  }
 
  @PrePersist
  protected void onCreate() {
    status = CuoponStatus.ACTIVE;
    createdAt = LocalDateTime.now();
  }

  @PreUpdate
  protected void onUpdate() {
    updatedAt = LocalDateTime.now();
  }
}
