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
public class CampaignProduct {
  private Long id;
  private Campaign campaign;
  private Product product;
  private Boolean enabled;
  private Double discountPercentage;
  private String quantity;
}
