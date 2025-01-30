package com.hhplus.ecommerce.interfaces.dto.point;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
public record UserPointRequest (
    Long userId,
    Long amount
){}
