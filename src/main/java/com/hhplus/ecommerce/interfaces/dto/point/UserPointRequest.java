package com.hhplus.ecommerce.interfaces.dto.point;

import com.hhplus.ecommerce.domain.point.UserPointCommand;
import lombok.Builder;

@Builder
public record UserPointRequest (
        Long userId,
        Long amount
){
    public UserPointCommand.Charge toCommand(){
        return UserPointCommand.Charge.builder()
                .userId(this.userId)
                .amount(this.amount)
                .build();
    }
}
