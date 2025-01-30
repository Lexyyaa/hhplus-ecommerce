package com.hhplus.ecommerce.interfaces.api.controller;

import com.hhplus.ecommerce.interfaces.dto.payment.PaymentRequest;
import com.hhplus.ecommerce.interfaces.dto.payment.PaymentResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pay")
@RequiredArgsConstructor
@Tag(name = "Payment", description = "결제 API")
public class PaymentController {

    @Operation(summary = "결제 요청", description = "사용자의 주문을 결제합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "결제 성공"),
    })
    @PostMapping("/pay/{orderId}")
    public ResponseEntity<PaymentResponse> pay(@RequestBody PaymentRequest req) {
        PaymentResponse res = PaymentResponse.builder()
                .orderId(1001L)
                .orderItems(List.of(
                        new PaymentResponse.OrderItemResponse(11L, 101L, 2L,10L, 12000L),
                        new PaymentResponse.OrderItemResponse(12L, 102L, 1L,10L,15000L)
                ))
                .userCouponId(req.userCouponId())
                .status("PAYMENT_COMPLETED")
                .method("CREDIT_CARD")
                .totalPaidAmount(37000L)
                .build();
        return ResponseEntity.ok(res);
    }

    @Operation(summary = "결제 취소", description = "사용자의 주문을 취소합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "결제 취소"),
    })
    @PostMapping("/cancel/{orderId}")
    public ResponseEntity<PaymentResponse> cancel(@Parameter(description = "주문 ID", required = true, example = "1")
                           @PathVariable("orderId") Long orderId) {
        PaymentResponse res = PaymentResponse.builder()
                .orderId(1001L)
                .orderItems(List.of(
                        new PaymentResponse.OrderItemResponse(11L, 101L, 2L,10L, 12000L),
                        new PaymentResponse.OrderItemResponse(12L, 102L, 1L,10L,15000L)
                ))
                .userCouponId(3L)
                .status("PAYMENT_FAILED")
                .method("CREDIT_CARD")
                .totalPaidAmount(37000L)
                .build();
        return ResponseEntity.ok(res);
    }
}