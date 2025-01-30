package com.hhplus.ecommerce.domain.point;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class UserPointCommand {

    @Getter
    @Builder
    public static class Charge {
        private Long userId;
        private Long amount;
    }
}
