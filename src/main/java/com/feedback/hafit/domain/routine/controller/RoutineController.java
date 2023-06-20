package com.feedback.hafit.domain.routine.controller;

import com.feedback.hafit.domain.routine.dto.PRoutineDTO;
import com.feedback.hafit.domain.routine.dto.RoutineDTO;
import com.feedback.hafit.domain.routine.service.RoutineService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/routines")
@RequiredArgsConstructor
@Slf4j
public class RoutineController {

    private final RoutineService routineService;

    @GetMapping("/all") // 루틴 조회 ( 관리자용 )
    public ResponseEntity<List<RoutineDTO>> getAllRoutines() {
        List<RoutineDTO> routines = routineService.getAllRoutines();
        return new ResponseEntity<>(routines, HttpStatus.OK);
    }

    @GetMapping("/{routineId}") // 하나의 루틴 조회
    public ResponseEntity<List<PRoutineDTO>> getRoutineById(@PathVariable Long routineId) {
        List<PRoutineDTO> routine = routineService.getRoutineById(routineId);
        return ResponseEntity.ok(routine);

    }

    @GetMapping // 한 회원에 모든 루틴 조회
    public ResponseEntity<List<RoutineDTO>> getUserRoutine(Principal principal) {
        return ResponseEntity.ok(routineService.getUserRoutine(principal.getName()));
    }

    @PostMapping // 루틴 추가
    public ResponseEntity<RoutineDTO> createRoutine(@RequestBody PRoutineDTO pRoutine, Principal principal) {
        RoutineDTO createdRoutine = routineService.createRoutine(pRoutine, principal.getName());
        return new ResponseEntity(createdRoutine, HttpStatus.CREATED);
    }

    @PutMapping("/{routineId}") // 루틴 수정
    public ResponseEntity<RoutineDTO> updateRoutine(@PathVariable Long routineId, @RequestBody PRoutineDTO pRoutineDTO, Principal principal) {

        return ResponseEntity.ok(routineService.updateRoutine(routineId, pRoutineDTO, principal.getName()));

    }

    @DeleteMapping("/{routineId}") // 루틴 삭제
    public ResponseEntity<Void> deleteRoutine(@PathVariable Long routineId) {
        routineService.deleteRoutine(routineId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
