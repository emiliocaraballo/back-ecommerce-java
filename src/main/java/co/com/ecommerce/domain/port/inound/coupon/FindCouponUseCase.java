package co.com.ecommerce.domain.port.inound.coupon;

import java.util.List;

import co.com.ecommerce.domain.model.Coupon;

public interface FindCouponUseCase {
  public List<Coupon> findAll();
}
