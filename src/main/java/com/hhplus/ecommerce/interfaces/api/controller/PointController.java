package com.hhplus.ecommerce.interfaces.api.controller;

import com.hhplus.ecommerce.interfaces.dto.point.UserPointRequest;
import com.hhplus.ecommerce.interfaces.dto.point.UserPointResponse;
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

@RestController
@RequestMapping("/api/point")
@RequiredArgsConstructor
@Tag(name = "UserPoint", description = "사용자포인트 API")
public class PointController {

    @Operation(summary = "포인트 충전", description = "사용자가 요청한 포인트만큼 충전합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "충전 성공",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = UserPointResponse.class)))
    })
    @PostMapping("/charge/{userId}")
    public ResponseEntity<UserPointResponse> charge(
            @Parameter(description = "사용자 ID", required = true, example = "1")
            @PathVariable("userId") Long userId,
            @RequestBody UserPointRequest req) {
        UserPointResponse res = UserPointResponse.builder()
                .userId(userId)
                .point(11000L)
                .build();
        return ResponseEntity.ok(res);
    }

    @Operation(summary = "포인트 조회", description = "사용자의 소유포인트를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회 성공",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = UserPointResponse.class)))
    })
    @GetMapping("/{userId}")
    public ResponseEntity<UserPointResponse> point(
            @Parameter(description = "사용자 ID", required = true, example = "1")
            @PathVariable("userId") Long userId) {
        UserPointResponse res = UserPointResponse.builder()
                .userId(userId)
                .point(12000L)
                .build();
        return ResponseEntity.ok(res);
    }
}