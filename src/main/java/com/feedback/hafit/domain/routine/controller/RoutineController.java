package com.feedback.hafit.domain.routine.controller;

import com.feedback.hafit.domain.routine.service.RoutineService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/todo")
@RequiredArgsConstructor
@Slf4j
public class RoutineController {

    private final RoutineService routineService;

//    @PostMapping // 운동 루틴 추가
//    public ResponseEntity<RoutineDTO> createRoutine(@RequestBody RoutineDTO routineDTO, Principal principal) {
//        RoutineDTO createRoutine = RoutineService.createRoutine(routineDTO, principal.getName());
//        return RequestEntity.ok(createRoutine);
//    }


}
