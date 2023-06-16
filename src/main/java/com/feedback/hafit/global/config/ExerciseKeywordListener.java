package com.feedback.hafit.global.config;

import com.feedback.hafit.domain.goal.entity.ExerciseKeyword;
import com.feedback.hafit.domain.goal.repository.ExerciseKeywordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ExerciseKeywordListener {

    // 디비 생성 시 키워드 값 insert
    private final ExerciseKeywordRepository exerciseKeywordRepository;

    @PostConstruct
    public void init() {
        List<String> initialKeywords = Arrays.asList(
                "다이어트",
                "밸런스 있는 몸 만들기",
                "넓은 어깨 만들기",
                "벌크업",
                "상체 강화",
                "하체 강화",
                "복근 만들기",
                "성취감 향상",
                "기타"
        );

        for (String keyword : initialKeywords) {
            ExerciseKeyword exerciseKeyword = new ExerciseKeyword(keyword);
            exerciseKeywordRepository.save(exerciseKeyword);
        }
    }
}
