package com.hhplus.ecommerce.domain.point;

import com.hhplus.ecommerce.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "point_historiy")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserPointHistory extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private UserPointType type;

    @Column(name = "amount", nullable = false)
    private Long amount;

    public enum UserPointType {
        CHARGE, PAY, REFUND, EXPIRED
    }
}
