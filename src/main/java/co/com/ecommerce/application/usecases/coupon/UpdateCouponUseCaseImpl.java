package co.com.ecommerce.application.usecases.coupon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.ecommerce.domain.model.Coupon;
import co.com.ecommerce.domain.port.inound.coupon.UpdateCouponUseCase;
import co.com.ecommerce.domain.port.outound.CouponRepositoryPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UpdateCouponUseCaseImpl implements UpdateCouponUseCase {
  
  private final CouponRepositoryPort couponRepositoryPort;

  @Override
  public Coupon updateCoupon(Coupon coupon) {
    return couponRepositoryPort.update(coupon);
  }
}