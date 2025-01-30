package com.hhplus.ecommerce.infra.repository.point;
import com.hhplus.ecommerce.domain.point.UserPoint;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;

public interface UserPointJPARepository extends JpaRepository<UserPoint, Long> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT u FROM UserPoint u WHERE u.id = :userId")
    Optional<UserPoint> findByUserIdWithLock(@Param("userId") Long userId);
}
