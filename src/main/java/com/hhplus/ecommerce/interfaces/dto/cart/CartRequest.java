package com.hhplus.ecommerce.interfaces.dto.cart;


import lombok.Builder;

import java.util.List;


@Builder
public record CartRequest(
        List<CartItem> items
) {
    public record CartItem(
            Long stockId,
            Long quantity,
            Long totalPrice
    ) {}
}