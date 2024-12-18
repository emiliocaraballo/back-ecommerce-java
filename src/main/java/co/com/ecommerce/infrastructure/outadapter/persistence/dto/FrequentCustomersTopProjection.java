package co.com.ecommerce.infrastructure.outadapter.persistence.dto;

public interface FrequentCustomersTopProjection {
  public Long getId();
  public String getName();
  public String getLastName();
  public Long getTotalSales();
  public Long getTotalAmount();
}
