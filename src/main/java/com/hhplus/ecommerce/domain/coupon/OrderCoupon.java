package com.hhplus.ecommerce.domain.coupon;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "order_coupon")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderCoupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_id", nullable = false)
    private Long orderId;

    @Column(name = "user_coupon_id", nullable = false)
    private Long user_coupon_id;

}
