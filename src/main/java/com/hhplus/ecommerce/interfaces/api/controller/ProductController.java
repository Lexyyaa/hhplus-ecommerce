package com.hhplus.ecommerce.interfaces.api.controller;

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
@RequestMapping("/api/product")
@RequiredArgsConstructor
@Tag(name = "Product", description = "상품 및 재고 API")
public class ProductController {

    @Operation(summary = "상품목록조회", description = "사용자가 요청한 브랜드에 대한 상품목록을 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "상품목록조회 성공",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductResponse.Product.class)))
    })
    @GetMapping("/brand/{brandId}")
    public ResponseEntity<List<ProductResponse.Product>> product(
            @Parameter(description = "브랜드 ID", required = true, example = "1")
            @PathVariable("brandId") Long brandId) {
        return ResponseEntity.ok(List.of(
                new ProductResponse.Product(101L, 100L, "상품 A"),
                new ProductResponse.Product(102L, 100L, "상품 B"),
                new ProductResponse.Product(103L, 101L, "상품 C")
        ));
    }

    @Operation(summary = "상품옵션별 재고조회", description = "사용자가 요청한 상품의 옵션별 재고를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "상품옵션별 재고조회 성공",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductResponse.ProductStock.class)))
    })
    @GetMapping("/stock/{productId}")
    public ResponseEntity<List<ProductResponse.ProductStock>> stock(
            @Parameter(description = "상품 ID", required = true, example = "1")
            @PathVariable("productId") Long productId) {
        return ResponseEntity.ok(List.of(
                new ProductResponse.ProductStock(1L, 101L, "001-101-00A","상품옵션 A",100L,15000L),
                new ProductResponse.ProductStock(1L, 101L, "001-101-00B","상품옵션 B",100L,16000L),
                new ProductResponse.ProductStock(1L, 101L, "001-101-00C","상품옵션 C",100L,17000L),
                new ProductResponse.ProductStock(1L, 101L, "001-101-00D","상품옵션 D",100L,18000L),
                new ProductResponse.ProductStock(1L, 101L, "001-101-00E","상품옵션 E",100L,19000L)
        ));
    }

    @Operation(summary = "인기상품조회", description = "최근3일중 판매량이 높은 5개의 상품목록을 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "인기상품조회 성공",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductResponse.PopularProduct.class)))
    })
    @GetMapping("/populoar")
    public ResponseEntity<List<ProductResponse.PopularProduct>> popular() {
        return ResponseEntity.ok(List.of(
                new ProductResponse.PopularProduct(101L, "상품 A", 1,3000L),
                new ProductResponse.PopularProduct(102L, "상품 B", 2,3000L),
                new ProductResponse.PopularProduct(103L, "상품 C", 3,3000L),
                new ProductResponse.PopularProduct(104L, "상품 D", 4,3000L),
                new ProductResponse.PopularProduct(105L, "상품 E", 5,3000L)
        ));
    }
}
