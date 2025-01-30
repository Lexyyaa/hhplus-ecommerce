package com.hhplus.ecommerce.interfaces.dto.payment;

public record PaymentRequest (
        Long orderId,
        Long userId,
        Long userCouponId
) {
}
