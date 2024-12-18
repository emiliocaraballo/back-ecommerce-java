package co.com.ecommerce.application.business.interfaces;

import java.util.List;

import co.com.ecommerce.domain.model.Coupon;

public interface CouponBusiness {
    public Coupon createCoupon(Coupon coupon);
    public Coupon updatedCoupon(Coupon coupon);
    public List<Coupon> findAll();
}