package com.hhplus.ecommerce.interfaces.api.controller;

import com.hhplus.ecommerce.domain.product.ProductService;
import com.hhplus.ecommerce.interfaces.dto.point.UserPointResponse;
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

    private final ProductService productService;

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
        List<ProductResponse.Product> res = ProductResponse.Product.from(productService.product(brandId));
        return ResponseEntity.ok(res);
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
        List<ProductResponse.ProductStock> res = ProductResponse.ProductStock.from(productService.stock(productId));
        return ResponseEntity.ok(res);
    }

    @Operation(summary = "인기상품조회", description = "최근3일중 판매량이 높은 5개의 상품목록을 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "인기상품조회 성공",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductResponse.PopularProduct.class)))
    })
    @GetMapping("/populoar")
    public ResponseEntity<List<ProductResponse.PopularProduct>> popular() {
        List<ProductResponse.PopularProduct> res = ProductResponse.PopularProduct.from(productService.popular());
        return ResponseEntity.ok(res);
    }
}
