package com.hhplus.ecommerce.interfaces.dto.point;

import com.hhplus.ecommerce.domain.point.UserPoint;
import com.hhplus.ecommerce.domain.point.UserPointInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserPointResponse {
    private Long userId;
    private Long point;

    public static UserPointResponse from(UserPointInfo point){
        return UserPointResponse.builder()
                .userId(point.getUserId())
                .point(point.getPoint())
                .build();
    }
}

