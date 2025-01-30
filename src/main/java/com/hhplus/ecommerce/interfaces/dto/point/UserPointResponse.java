package com.hhplus.ecommerce.interfaces.dto.point;

@Builder
public record UserPointResponse (
        Long userId,
        Long point
){
    public static UserPointResponse from(UserPointInfo point){
        return UserPointResponse.builder()
                .userId(point.getUserId())
                .point(point.getPoint())
                .build();
    }
}
