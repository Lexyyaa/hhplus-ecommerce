package com.hhplus.ecommerce.interfaces.api.controller;

import com.hhplus.ecommerce.interfaces.dto.cart.CartRequest;
import com.hhplus.ecommerce.interfaces.dto.cart.CartResponse;
import com.hhplus.ecommerce.interfaces.dto.product.ProductResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
@Tag(name = "Cart", description = "장바구니 API")
public class CartController {

    @Operation(summary = "장바구니 추가", description = "사용자가 요청한 상품을 장바구니에 추가합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "장바구니 추가 성공")
    })
    @PostMapping("/add")
    public ResponseEntity<CartResponse> add(@RequestBody CartRequest req) {
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "장바구니 삭제", description = "사용자가 요청한 상품을 장바구니에서 삭제합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "장바구니 추가 성공",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductResponse.Product.class)))
    })
    @PostMapping("/delete")
    public ResponseEntity<CartResponse> delete(@RequestBody CartRequest req) {
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "장바구니 조회", description = "사용자의 장바구니 상품 목록을 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "장바구니 조회 성공",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CartResponse.class)))
    })
    @GetMapping("/list/{userId}")
    public ResponseEntity<List<CartResponse>> list(
            @Parameter(description = "사용자 ID", required = true, example = "1")
            @PathVariable("userId") Long userId
    ) {
        return ResponseEntity.ok(List.of(
                new CartResponse(101L,"1번재고",2L,2000L),
                new CartResponse(102L,"2번재고",2L,2000L),
                new CartResponse(103L,"3번재고",2L,2000L),
                new CartResponse(104L,"4번재고",2L,2000L),
                new CartResponse(105L,"5번재고",2L,2000L)
        ));
    }

    @Operation(summary = "장바구니 상품수량 변경", description = "상품 수량을 변경합니다 (수량 0이면 삭제).")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "장바구니 상품수량 변경 성공",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductResponse.Product.class)))
    })
    @PostMapping("/update")
    public ResponseEntity<CartResponse> update(@RequestBody CartRequest req) {
        return ResponseEntity.noContent().build();
    }
}
