package com.hhplus.ecommerce.domain.coupon;

import com.hhplus.ecommerce.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "coupon")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Coupon extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "coupon_code", nullable = false)
    private String couponCode;

    @Column(name = "coupon_name", nullable = false)
    private String couponName;

    @Column(name = "discount_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private CouponType type;

    @Column(name = "discount_target", nullable = false)
    @Enumerated(EnumType.STRING)
    private CouponTarget target;

    @Column(name = "quantity", nullable = false)
    private Long quantity;

    @Column(name = "discount_amount")
    private Long discountAmount;

    @Column(name = "discount_rate")
    private Long discountRate;

    @Column(name = "min_order_amount")
    private Long minOrderAmount;

    @Column(name = "max_discount_amount")
    private Long maxDiscountAmount;

    private enum CouponType {
        RATE, AMOUNT
    }

    private enum CouponTarget {
        ORDER
    }

}
