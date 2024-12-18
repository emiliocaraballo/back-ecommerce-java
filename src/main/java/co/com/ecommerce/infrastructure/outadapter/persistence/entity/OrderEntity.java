package co.com.ecommerce.infrastructure.outadapter.persistence.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "orders")
public class OrderEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private UserEntity user;

  @Column(nullable = false)
  private BigDecimal totalAmount;

  @Column(nullable = false)
  private LocalDateTime orderDate = LocalDateTime.now();

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private OrderStatus status;

  @Column(nullable = false)
  private String paymentMethod;

  @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<OrderItemEntity> orderItems;

  /* audit */
  @Column(name = "created_at")
  private LocalDateTime createdAt;

  @Column(name = "created_by")
  private Long createdBy;

  @Column(name = "updated_at")
  private LocalDateTime updatedAt;

  @Column(name = "updated_by")
  private Long updatedBy;

  @PrePersist
  public void onCreate() {
    if (this.status == null) {
      this.status = OrderStatus.PENDING;
    }
    this.orderDate = LocalDateTime.now();
    this.createdAt = LocalDateTime.now();
  }

  @PreUpdate
  public void onUpdate() {
    if (this.status == null) {
      this.status = OrderStatus.PENDING;
    }
    this.updatedAt = LocalDateTime.now();
  }

  public enum OrderStatus {
    PENDING, // Pendiente
    SHIPPED, // Enviado
    DELIVERED, // Entregado
    CANCELLED, // Cancelado
    REFUNDED // Reembolsado
  }
}
