package com.hhplus.ecommerce.interfaces.api.controller;

import com.hhplus.ecommerce.interfaces.dto.coupon.CouponResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/coupon")
@RequiredArgsConstructor
@Tag(name = "Coupon", description = "쿠폰 API")
public class CouponController {

    @Operation(summary = "선착순 쿠폰발급", description = "선착순으로 쿠폰을 발급합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "쿠폰 발급 성공"),
    })
    @PostMapping("/issue/{userId}")
    public ResponseEntity<CouponResponse> issue(@Parameter(description = "사용자 ID", required = true, example = "1")
                          @PathVariable("userId") Long userId) {
        CouponResponse res = CouponResponse.builder()
                .userId(userId)
                .couponCode("111-AAA")
                .couponName("10%할인쿠폰")
                .expiredAt(LocalDateTime.now().plusDays(7))
                .build();
        return ResponseEntity.ok(res);
    }

    @Operation(summary = "보유쿠폰 목록조회", description = "사용자에게 발급된 쿠폰을 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "쿠폰 목록조회 성공"),
    })
    @GetMapping("/list/{userId}")
    public ResponseEntity<List<CouponResponse>> list(@Parameter(description = "사용자 ID", required = true, example = "1")
                         @PathVariable("userId") Long userId) {
        return ResponseEntity.ok(List.of(
                new CouponResponse(101L,11L,"AAA-001","1번품목 10% 할인쿠폰",LocalDateTime.now()),
                new CouponResponse(102L,11L,"AAA-002","2번품목 10% 할인쿠폰",LocalDateTime.now()),
                new CouponResponse(103L,11L,"AAA-003","3번품목 10% 할인쿠폰",LocalDateTime.now()),
                new CouponResponse(104L,11L,"AAA-004","4번품목 10% 할인쿠폰",LocalDateTime.now()),
                new CouponResponse(105L,11L,"AAA-005","5번품목 10% 할인쿠폰",LocalDateTime.now())
        ));
    }
}

