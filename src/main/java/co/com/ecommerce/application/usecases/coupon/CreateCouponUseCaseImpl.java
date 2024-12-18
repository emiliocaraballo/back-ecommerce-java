package  co.com.ecommerce.application.usecases.coupon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.ecommerce.domain.model.Coupon;
import co.com.ecommerce.domain.port.inound.coupon.CreateCouponUseCase;
import co.com.ecommerce.domain.port.outound.CouponRepositoryPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class CreateCouponUseCaseImpl implements CreateCouponUseCase {
  
  private final CouponRepositoryPort couponRepositoryPort;

  @Override
  public Coupon createCoupon(Coupon coupon) {
    return couponRepositoryPort.save(coupon);
  }
}