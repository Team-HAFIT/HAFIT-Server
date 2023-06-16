package com.feedback.hafit.domain.plan.controller;

import com.feedback.hafit.domain.plan.dto.PlanDTO;
import com.feedback.hafit.domain.plan.service.PlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/plans")
@RequiredArgsConstructor
public class PlanController {

    @Autowired
    private PlanService planService;

    @PostMapping("") // 운동 시작 전 세팅
    public ResponseEntity<PlanDTO> create(@RequestBody PlanDTO planDTO, Principal principal) {
        PlanDTO dto = planService.setting(planDTO, principal.getName());
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{planId}") // 계획 조회
    public ResponseEntity<PlanDTO> find(@PathVariable Long planId) {
        PlanDTO dto = planService.getDTOById(planId);
        return ResponseEntity.ok(dto);
    }
}