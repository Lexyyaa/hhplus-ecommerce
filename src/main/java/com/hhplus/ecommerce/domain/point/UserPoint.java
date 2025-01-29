package com.hhplus.ecommerce.domain.point;

import com.hhplus.ecommerce.interfaces.dto.point.UserPointResponse;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserPoint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "point")
    private Long point;

    public void charge(Long amount){
        if(amount <= 0){
            throw new IllegalArgumentException("충전금액은 0보다 커야합니다.");
        }
        this.point += amount;
    }

    @Override
    public String toString() {
        return "UserPoint{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", point=" + point +
                '}';
    }
}
