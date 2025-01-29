package com.hhplus.ecommerce.interfaces.dto.point;

import com.hhplus.ecommerce.domain.point.UserPointCommand;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserPointRequest {
    private Long userId;
    private Long amount;

    public UserPointCommand.Charge toCommand(){
        return UserPointCommand.Charge.builder()
                .userId(this.userId)
                .amount(this.amount)
                .build();
    }
}
