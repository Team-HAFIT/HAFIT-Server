package com.feedback.hafit.domain.goal.controller;

import com.feedback.hafit.domain.goal.dto.response.KeywordDTO;
import com.feedback.hafit.domain.goal.service.KeywordService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/keywords")
public class KeywordController {
    private final KeywordService keywordService;

    @GetMapping()
    public List<KeywordDTO> getKeywords() {
        return keywordService.getKeywords();
    }
}
