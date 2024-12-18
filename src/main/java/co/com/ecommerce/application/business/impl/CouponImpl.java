package co.com.ecommerce.application.business.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.ecommerce.application.business.interfaces.CouponBusiness;
import co.com.ecommerce.domain.model.Coupon;
import co.com.ecommerce.domain.port.inound.coupon.CreateCouponUseCase;
import co.com.ecommerce.domain.port.inound.coupon.FindCouponUseCase;
import co.com.ecommerce.domain.port.inound.coupon.UpdateCouponUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class CouponImpl implements CouponBusiness {
    private final CreateCouponUseCase createCouponUseCase;
    private final UpdateCouponUseCase updateCouponUseCase;
    private final FindCouponUseCase findCouponUseCase;

    @Override
    public Coupon createCoupon(Coupon coupon) {
        return createCouponUseCase.createCoupon(coupon);
    }

    @Override
    public Coupon updatedCoupon(Coupon coupon) {
        return updateCouponUseCase.updateCoupon(coupon);
    }

    @Override
    public List<Coupon> findAll() {
        return findCouponUseCase.findAll();
    }
  }