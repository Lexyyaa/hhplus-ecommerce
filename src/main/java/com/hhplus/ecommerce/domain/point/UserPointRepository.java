package com.hhplus.ecommerce.domain.point;

public interface UserPointRepository {
    UserPoint findById(Long userId);
    UserPoint findByIdWithLock(Long userId);
    UserPoint save(UserPoint userPoint);
}
