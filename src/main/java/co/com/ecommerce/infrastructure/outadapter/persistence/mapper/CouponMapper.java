package co.com.ecommerce.infrastructure.outadapter.persistence.mapper;

import org.springframework.stereotype.Component;

import co.com.ecommerce.application.dto.request.CouponCreateDTO;
import co.com.ecommerce.application.dto.response.CouponListResponse;
import co.com.ecommerce.domain.model.Coupon;
import co.com.ecommerce.infrastructure.outadapter.persistence.entity.CouponEntity;
import co.com.ecommerce.infrastructure.outadapter.persistence.entity.CouponEntity.CuoponStatus;

@Component
public class CouponMapper {
  
    public Coupon toDomain(CouponEntity entity) {
        if (entity == null) {
            return null;
        }
        Coupon coupon = new Coupon();
        coupon.setId(entity.getId());
        coupon.setCode(entity.getCode());
        coupon.setDiscountPercentage(entity.getDiscountPercentage());
        coupon.setStartDate(entity.getStartDate());
        coupon.setEndDate(entity.getEndDate());
        coupon.setLimit(entity.getLimit());
        coupon.setUserStatus(entity.getUserStatus());
        coupon.setStatus(entity.getStatus().name());
        return coupon;
    }

    public CouponEntity toEntity(Coupon domain) {
        if (domain == null) {
            return null;
        }
        CouponEntity entity = new CouponEntity();
        entity.setId(domain.getId());
        entity.setCode(domain.getCode());
        entity.setDiscountPercentage(domain.getDiscountPercentage());
        entity.setStartDate(domain.getStartDate());
        entity.setEndDate(domain.getEndDate());
        entity.setLimit(domain.getLimit());
        entity.setUserStatus(domain.getUserStatus());
        entity.setStatus(CuoponStatus.valueOf(domain.getStatus()));
        return entity;
    }

    public Coupon dtoToModel(CouponCreateDTO dto) {
        Coupon coupon = new Coupon();
        coupon.setId(null);
        coupon.setCode(dto.getCode());
        coupon.setDiscountPercentage(dto.getDiscountPercentage());
        coupon.setStartDate(dto.getStartDate());
        coupon.setEndDate(dto.getEndDate());
        coupon.setLimit(dto.getLimit());
        coupon.setUserStatus(dto.getUserStatus());
        coupon.setStatus(dto.getStatus());
        return coupon;
    }

    public CouponListResponse toResponse(Coupon entity) {
        if (entity == null) {
            return null;
        }
        CouponListResponse coupon = new CouponListResponse();
        coupon.setId(entity.getId());
        coupon.setCode(entity.getCode());
        coupon.setDiscountPercent(entity.getDiscountPercentage());
        coupon.setStartDate(entity.getStartDate());
        coupon.setEndDate(entity.getEndDate());
        coupon.setMaxLimit(entity.getLimit());
        coupon.setIsUserStatus(entity.getUserStatus());
        coupon.setStatus(entity.getStatus());
        return coupon;
    }

    public CouponEntity merge(CouponEntity newData, CouponEntity existingData) {
        if (newData.getCode() != null) {
            existingData.setCode(newData.getCode());
        }
        if (newData.getDiscountPercentage() != null) {
            existingData.setDiscountPercentage(newData.getDiscountPercentage());
        }
        if (newData.getStartDate() != null) {
            existingData.setStartDate(newData.getStartDate());
        }
        if (newData.getEndDate() != null) {
            existingData.setEndDate(newData.getEndDate());
        }
        if (newData.getLimit() != null) {
            existingData.setLimit(newData.getLimit());
        }
        if (newData.getUserStatus() != null) {
            existingData.setUserStatus(newData.getUserStatus());
        }
        if (newData.getStatus() != null) {
            existingData.setStatus(newData.getStatus());
        }
        return existingData;
    }
}
