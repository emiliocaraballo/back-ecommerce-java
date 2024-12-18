package co.com.ecommerce.infrastructure.inadapter.rest.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import co.com.ecommerce.application.business.interfaces.CouponBusiness;
import co.com.ecommerce.application.dto.request.CouponCreateDTO;
import co.com.ecommerce.application.dto.response.CouponListResponse;
import co.com.ecommerce.domain.model.Coupon;
import co.com.ecommerce.infrastructure.outadapter.persistence.mapper.CouponMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("${application.request.mappings}/v1/coupon")
@Tag(name = "coupon", description = "Coupon")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Validated
@Slf4j
public class CouponController {

  private final CouponBusiness couponBusiness;
  private final CouponMapper couponMapper;


  @GetMapping( value = "/list")
  public Mono<List<CouponListResponse>> getList(){
    List<CouponListResponse> list = new ArrayList<>();
    List<Coupon> coupons = couponBusiness.findAll();
    if(coupons.size() > 0) {
      list.addAll(coupons.stream().map(couponMapper::toResponse).toList());
    }
    return Mono.just(list);
  }

  @PostMapping(value = "create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.CREATED)
  public Mono<CouponListResponse> create(
    @Valid @RequestBody CouponCreateDTO body
  ){
    Coupon coupon = couponMapper.dtoToModel(body);
    Coupon response = couponBusiness.createCoupon(coupon);
    return Mono.just(couponMapper.toResponse(response));
  }

  @PutMapping( value = "/update/{id}")
  public Mono<CouponListResponse> update(@PathVariable Long id, @Valid @RequestBody CouponCreateDTO body){
    Coupon coupon = couponMapper.dtoToModel(body);
    coupon.setId(id);
    Coupon response = couponBusiness.updatedCoupon(coupon);
    return Mono.just(couponMapper.toResponse(response));
  }
  
}
