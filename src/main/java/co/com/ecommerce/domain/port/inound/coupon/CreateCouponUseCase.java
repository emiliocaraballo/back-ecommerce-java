package co.com.ecommerce.domain.port.inound.coupon;

import co.com.ecommerce.domain.model.Coupon;

public interface CreateCouponUseCase {
    public Coupon createCoupon(Coupon coupon);
}
