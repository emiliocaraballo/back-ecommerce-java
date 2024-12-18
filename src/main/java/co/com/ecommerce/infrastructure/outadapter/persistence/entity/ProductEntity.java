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
@Table(name = "products")
public class ProductEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false, name = "trademark")
  private String tradeMark;

  @Column(nullable = false)
  private String description;

  @Column(nullable = false)
  private String image;

  @Column(nullable = false)
  private Double price;

  @Column(nullable = false)
  private Integer stock;

  @Enumerated(EnumType.STRING)
  private ProductStatus status;

  /* audit */
  @Column(name = "created_at")
  private LocalDateTime createdAt;

  @Column(name = "created_by")
  private Long createdBy;

  @Column(name = "updated_at")
  private LocalDateTime updatedAt;

  @Column(name = "updated_by")
  private Long updatedBy;

  public enum ProductStatus {
    ACTIVE, INACTIVE, OUT_OF_STOCK
  }

  @PrePersist
  protected void onCreate() {
    createdAt = LocalDateTime.now();
    status = ProductStatus.ACTIVE;
  }

  @PreUpdate
  protected void onUpdate() {
    updatedAt = LocalDateTime.now();
  }

}
