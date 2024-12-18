package co.com.ecommerce.infrastructure.outadapter.persistence.dto;

public interface FindTopMostSoldProductsProjection  {
  public Long getId();
  public String getName();
  public Long getTotalSales();
  public String getImage();
}
