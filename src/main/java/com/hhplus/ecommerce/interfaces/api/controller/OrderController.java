package com.hhplus.ecommerce.interfaces.api.controller;

import com.hhplus.ecommerce.interfaces.dto.order.OrderRequest;
import com.hhplus.ecommerce.interfaces.dto.order.OrderResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
@Tag(name = "Order", description = "주문 API")
public class OrderController {

    @Operation(summary = "상품주문", description = "상품을 주문합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "상품상세전공 성공"),
    })
    @PostMapping("/{id}")
    public ResponseEntity<OrderResponse> order(@RequestBody OrderRequest req){
        OrderResponse res = OrderResponse.builder()
                .orderId(1001L)
                .orderItems(List.of(
                        new OrderResponse.OrderItemResponse(11L, 101L, 2L,10L, 12000L),
                        new OrderResponse.OrderItemResponse(12L, 102L, 1L,10L,15000L)
                ))
                .userCouponId(req.userCouponId())
                .status("ORDERED")
                .build();
        return ResponseEntity.ok(res);
    }
}
