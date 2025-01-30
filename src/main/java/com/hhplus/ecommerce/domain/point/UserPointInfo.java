package com.hhplus.ecommerce.domain.point;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserPointInfo {
    private Long userId;
    private String name;
    private Long point;

    public static UserPointInfo from(UserPoint userPoint){
        return UserPointInfo.builder()
                .userId(userPoint.getId())
                .name(userPoint.getName())
                .point(userPoint.getPoint())
                .build();
    }

    @Override
    public String toString() {
        return "UserPointInfo{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", point=" + point +
                '}';
    }
}
