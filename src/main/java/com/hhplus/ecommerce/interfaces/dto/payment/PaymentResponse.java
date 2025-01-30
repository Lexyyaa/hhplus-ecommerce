package com.hhplus.ecommerce.interfaces.dto.payment;

import lombok.Builder;

import java.util.List;
@Builder
public record PaymentResponse(
        Long orderId,
        List<OrderItemResponse> orderItems,
        Long userCouponId,
        String status,
        String method,
        Long totalPaidAmount
) {
    public record OrderItemResponse(
            Long id,
            Long orderId,
            Long stockId,
            Long quantity,
            Long price
    ) {    }
}