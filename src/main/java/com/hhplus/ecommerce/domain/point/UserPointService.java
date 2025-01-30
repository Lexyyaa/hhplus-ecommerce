package com.hhplus.ecommerce.domain.point;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserPointService {

    private final UserPointRepository userPointRepository;

    public UserPointInfo charge(UserPointCommand.Charge command){
        UserPoint currUserPoint = userPointRepository.findByIdWithLock(command.getUserId());

        currUserPoint.charge(command.getAmount());
        UserPoint userPoint = userPointRepository.save(currUserPoint);

        return UserPointInfo.from(userPoint);
    }

    public UserPointInfo point(Long userId) {
        UserPoint userPoint = userPointRepository.findById(userId);

        return UserPointInfo.from(userPoint);
    }
}
