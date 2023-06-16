package com.feedback.hafit.domain.plan.controller;

import com.feedback.hafit.domain.plan.dto.request.PlanRequestDTO;
import com.feedback.hafit.domain.plan.dto.response.PlanResponseDTO;
import com.feedback.hafit.domain.plan.service.PlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/plans")
@RequiredArgsConstructor
public class PlanController {

    private final PlanService planService;

    @PostMapping // 운동 시작 전 세팅
    public ResponseEntity<PlanResponseDTO> createPlan(@RequestBody PlanRequestDTO planRequestDTO, Principal principal) {
        PlanResponseDTO dto = planService.settingPlan(planRequestDTO, principal.getName());
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{planId}") // 계획 조회
    public ResponseEntity<PlanResponseDTO> find(@PathVariable Long planId) {
        PlanResponseDTO dto = planService.getPlanById(planId);
        return ResponseEntity.ok(dto);
    }
}