package com.hhplus.ecommerce.infra.repository.point;

import com.hhplus.ecommerce.domain.point.UserPoint;
import com.hhplus.ecommerce.domain.point.UserPointInfo;
import com.hhplus.ecommerce.domain.point.UserPointRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserPointRepositoryImpl implements UserPointRepository {

    private final UserPointJPARepository jpaRepository;

    @Override
    public UserPoint findById(Long userId) {
        return jpaRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("UserPoint를 찾을 수 없습니다. userId: " + userId)
        );
    }

    @Override
    public UserPoint findByIdWithLock(Long userId) {
        return jpaRepository.findByUserIdWithLock(userId).orElseThrow(
                () -> new IllegalArgumentException("UserPoint를 찾을 수 없습니다. userId: " + userId)
        );
    }

    @Override
    public UserPoint save(UserPoint userPoint) {
        return jpaRepository.save(userPoint);
    }
}
