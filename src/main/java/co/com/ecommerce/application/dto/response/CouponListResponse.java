package co.com.ecommerce.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CouponListResponse {
  private Long id;
  private String code;
  private Double discountPercent;
  private String startDate;
  private String endDate;
  private Integer maxLimit;
  private Boolean isUserStatus;
  private String status;
}
