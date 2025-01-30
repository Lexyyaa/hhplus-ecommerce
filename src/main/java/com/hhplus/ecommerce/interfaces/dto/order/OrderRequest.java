package com.hhplus.ecommerce.interfaces.dto.order;

import lombok.Builder;

import java.util.List;

@Builder
public record OrderRequest(
        Long userId,
        List<OrderItem> items,
        Long userCouponId
) {
    public record OrderItem(
            Long stockId,
            Long quantity,
            Long totalPrice

    ) {}
}