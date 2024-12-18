package co.com.ecommerce.application.usecases.coupon;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.ecommerce.domain.model.Coupon;
import co.com.ecommerce.domain.port.inound.coupon.FindCouponUseCase;
import co.com.ecommerce.domain.port.outound.CouponRepositoryPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class FindCouponUseCaseImpl implements FindCouponUseCase {
  
  private final CouponRepositoryPort couponRepositoryPort;

  @Override
  public List<Coupon> findAll() {
    return couponRepositoryPort.findAll();
  }
}