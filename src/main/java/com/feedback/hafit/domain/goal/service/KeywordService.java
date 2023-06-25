package com.feedback.hafit.domain.goal.service;

import com.feedback.hafit.domain.goal.dto.response.KeywordDTO;
import com.feedback.hafit.domain.goal.entity.Keyword;
import com.feedback.hafit.domain.goal.repository.KeywordRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class KeywordService {
    private final KeywordRepository keywordRepository;

    public List<KeywordDTO> getKeywords() {
        List<Keyword> keywords = keywordRepository.findAll();

        List<KeywordDTO> keywordDTOs = new ArrayList<>();

        for (Keyword keyword : keywords) {
            KeywordDTO keywordDTO = new KeywordDTO(keyword);
            keywordDTOs.add(keywordDTO);
        }

        return keywordDTOs;
    }
}
