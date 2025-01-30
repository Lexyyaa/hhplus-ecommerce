package com.hhplus.ecommerce.interfaces.dto.coupon;

import com.hhplus.ecommerce.domain.coupon.Coupon;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record CouponResponse (
        Long id,
        Long userId,
        String couponCode,
        String couponName,
        LocalDateTime expiredAt
){}
