package com.hhplus.ecommerce.interfaces.dto.cart;

import lombok.Builder;

import java.util.List;

@Builder
public record CartResponse(
    Long stockId,
    String name,
    Long quantity,
    Long totalPrice
) {}
