package co.com.ecommerce.domain.port.outound;

import java.util.List;

import co.com.ecommerce.domain.model.Coupon;

public interface CouponRepositoryPort {
    public Coupon save(Coupon coupon);
    public List<Coupon> findAll();
    public Coupon update(Coupon coupon);
}
