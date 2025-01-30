package com.hhplus.ecommerce.unit.point;

import com.hhplus.ecommerce.domain.point.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@Slf4j
@ExtendWith(MockitoExtension.class)
class UserPointServiceTest {

    @Mock
    private UserPointRepository userPointRepository;

    @InjectMocks
    private UserPointService sut;

    @Nested
    @DisplayName("포인트 충전")
    class Charge{
        @Test
        @DisplayName("[성공]_포인트_충전_성공")
        void 포인트_충전_성공(){
            Long userId = 10L;
            String userName = "사용자10";
            Long point = 1000L;

            UserPoint currUserPoint = Mockito.spy(UserPoint.builder()
                    .id(userId)
                    .name(userName)
                    .point(point).build());

            Long commandUserId = 10L;
            Long commandAmount = 2000L;

            UserPoint userPoint = UserPoint.builder()
                    .id(userId)
                    .name(userName)
                    .point(point+commandAmount).build();

            UserPointCommand.Charge command = UserPointCommand.Charge.builder()
                    .userId(commandUserId)
                    .amount(commandAmount)
                    .build();

            when(userPointRepository.findByIdWithLock(commandUserId)).thenReturn(currUserPoint);
            when(userPointRepository.save(any(UserPoint.class))).thenReturn(userPoint);

            UserPointInfo userPointInfo = sut.charge(command);

            assertEquals(userPointInfo.getUserId(),userId);
            assertEquals(userPointInfo.getPoint(),point+commandAmount);
            verify(currUserPoint).charge(commandAmount);
        }

        @Test
        @DisplayName("[실패]_유효하지않은_userId가_들어왔을_때")
        void 포인트_충전__유효하지않은_userId가_들어왔을때(){
            Long commandUserId = 10L;
            Long commandAmount = 2000L;

            UserPointCommand.Charge command = UserPointCommand.Charge.builder()
                    .userId(commandUserId)
                    .amount(commandAmount)
                    .build();

            when(userPointRepository.findByIdWithLock(commandUserId))
                    .thenThrow(new IllegalArgumentException("UserPoint를 찾을 수 없습니다. userId: " + commandUserId));

            Exception exception = assertThrows(IllegalArgumentException.class,
                    () -> sut.charge(command));

            assertEquals(exception.getClass(),IllegalArgumentException.class);
            verify(userPointRepository).findByIdWithLock(commandUserId);
        }

        @Test
        @DisplayName("[실패]_유효하지않은_amount가_들어왔을_때")
        public void 유효하지않은_amount가_들어왔을_때(){
            Long userId = 10L;
            String userName = "사용자10";
            Long point = 1000L;

            UserPoint currUserPoint = Mockito.spy(UserPoint.builder()
                    .id(userId)
                    .name(userName)
                    .point(point).build());

            Long commandUserId = 10L;
            Long commandAmount = 0L;

            UserPoint userPoint = UserPoint.builder()
                    .id(userId)
                    .name(userName)
                    .point(point+commandAmount).build();

            UserPointCommand.Charge command = UserPointCommand.Charge.builder()
                    .userId(commandUserId)
                    .amount(commandAmount)
                    .build();

            when(userPointRepository.findByIdWithLock(commandUserId)).thenReturn(currUserPoint);

            Exception exception = assertThrows(IllegalArgumentException.class,
                    () -> sut.charge(command));

            assertEquals(exception.getClass(),IllegalArgumentException.class);
            verify(userPointRepository).findByIdWithLock(commandUserId);
            verify(currUserPoint).charge(commandAmount);
        }
    }

    @Nested
    @DisplayName("포인트 조회")
    class Point{
        @Test
        @DisplayName("[성공]_포인트_조회_성공")
        void 포인트_조회_성공(){
            Long userId = 10L;
            String userName = "사용자10";
            Long point = 1000L;
            UserPoint user = UserPoint.builder()
                    .id(userId)
                    .name(userName)
                    .point(point).build();

            when(userPointRepository.findById(userId)).thenReturn(user);

            UserPointInfo userPointInfo = sut.point(userId);

            assertEquals(userPointInfo.getUserId(),userId);
            assertEquals(userPointInfo.getName(),userName);
            assertEquals(userPointInfo.getPoint(),point);
            verify(userPointRepository).findById(userId);
        }

        @Test
        @DisplayName("[실패]_유효하지않은_USERID가_들어왔을_때")
        void 유효하지않은_USERID가_들어왔을때(){

            Long userId = 99L;

            when(userPointRepository.findById(userId))
                    .thenThrow(new IllegalArgumentException("UserPoint를 찾을 수 없습니다. userId: " + userId));

            Exception exception = assertThrows(IllegalArgumentException.class,
                    () -> sut.point(userId));

            assertEquals(exception.getClass(),IllegalArgumentException.class);
            verify(userPointRepository).findById(userId);
        }
    }
}