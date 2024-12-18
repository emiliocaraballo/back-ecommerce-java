package co.com.ecommerce.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Coupon {
  private Long id;
  private String code;
  private Double discountPercentage;
  private String startDate;
  private String endDate;
  private int limit;
  private Boolean userStatus;
  private String status;
}
