package com.hhplus.ecommerce.interfaces.dto.order;

import lombok.Builder;

import java.util.List;

@Builder
public record OrderResponse(
        Long orderId,
        List<OrderItemResponse> orderItems,
        Long userCouponId,
        String status
){
    public record OrderItemResponse(
            Long id,
            Long orderId,
            Long stockId,
            Long quantity,
            Long price
    ) {    }
}