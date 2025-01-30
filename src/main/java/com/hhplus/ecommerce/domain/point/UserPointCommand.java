package com.hhplus.ecommerce.domain.point;

import lombok.Builder;
import lombok.Getter;

public class UserPointCommand {

    @Getter
    @Builder
    public static class Charge {
        private Long userId;
        private Long amount;
    }
}
