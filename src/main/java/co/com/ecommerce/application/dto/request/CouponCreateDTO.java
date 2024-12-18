package co.com.ecommerce.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class CouponCreateDTO {
  @NotBlank(message = "El código del cupón no puede estar vacío")
  private String code;

  @Positive(message = "La porcentaje de descuento debe ser mayor que cero")
  private Double discountPercentage;

  @NotBlank(message = "La fecha de inicio del cupón no puede estar vacío")
  private String startDate;

  @NotBlank(message = "La fecha de fin del cupón no puede estar vacío")
  private String endDate;

  @Positive(message = "El límite del cupón debe ser mayor que cero")
  private Integer limit;

  // @NotNull(message = "El estado del cupón no puede estar vacío")
  private Boolean userStatus;

  // @NotBlank(message = "El estado del cupón no puede estar vacío")
  private String status;
}
