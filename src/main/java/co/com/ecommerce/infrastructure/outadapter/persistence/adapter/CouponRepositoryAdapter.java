package co.com.ecommerce.infrastructure.outadapter.persistence.adapter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.com.ecommerce.domain.model.Coupon;
import co.com.ecommerce.domain.port.outound.CouponRepositoryPort;
import co.com.ecommerce.infrastructure.outadapter.persistence.entity.CouponEntity;
import co.com.ecommerce.infrastructure.outadapter.persistence.mapper.CouponMapper;
import co.com.ecommerce.infrastructure.outadapter.persistence.repository.CouponJpaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class CouponRepositoryAdapter implements CouponRepositoryPort {
    private final CouponJpaRepository jpaRepository;
    private final CouponMapper couponMapper;

    @Override
    @Transactional
    public Coupon save(Coupon coupon) {
        log.info("save coupon: {}", coupon);
        CouponEntity entity = couponMapper.toEntity(coupon);
        CouponEntity savedEntity = jpaRepository.save(entity);
        return couponMapper.toDomain(savedEntity);
    }

    @Override
    public List<Coupon> findAll() {
        log.info("find all coupons");
        return jpaRepository.findAll().stream().map(couponMapper::toDomain).toList();
    }

    @Override
    @Transactional
    public Coupon update(Coupon coupon) {
        log.info("update coupon: {}", coupon);
        CouponEntity entity = couponMapper.toEntity(coupon);
        CouponEntity result = jpaRepository.save(entity);
        return couponMapper.toDomain(result);
    }
}
