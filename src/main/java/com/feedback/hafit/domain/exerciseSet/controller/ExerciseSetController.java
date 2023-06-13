package com.feedback.hafit.domain.exerciseSet.controller;

import com.feedback.hafit.domain.exerciseSet.dto.ExerciseSetDTO;
import com.feedback.hafit.domain.exerciseSet.service.ExerciseSetService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/sets")
@RequiredArgsConstructor
public class ExerciseSetController {

    @Autowired
    private ExerciseSetService exerciseSetService;

    @PostMapping("")
    public ExerciseSetDTO exec(@RequestBody ExerciseSetDTO exerciseSetDTO, Principal principal) {
        return exerciseSetService.save(exerciseSetDTO, principal.getName());
    }

//    @GetMapping("{planId}")
//    public ExerciseSetDTO findPlan() {
//        return exerciseSetService.
//    }
}