package com.hhplus.ecommerce.interfaces.api.controller;

import com.hhplus.ecommerce.domain.point.UserPointService;
import com.hhplus.ecommerce.interfaces.dto.point.UserPointRequest;
import com.hhplus.ecommerce.interfaces.dto.point.UserPointResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/point")
@RequiredArgsConstructor
public class UserPointController {

    private final UserPointService userPointService;

    @PostMapping("/charge/{userId}")
    public ResponseEntity<UserPointResponse> charge(@PathVariable("userId") Long userId , @RequestBody UserPointRequest req) {
        UserPointResponse res = UserPointResponse.from(userPointService.charge(req.toCommand()));
        return ResponseEntity.ok(res);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserPointResponse> point(@PathVariable("userId") Long userId) {
        UserPointResponse res = UserPointResponse.from(userPointService.point(userId));
        return ResponseEntity.ok(res);
    }
}

