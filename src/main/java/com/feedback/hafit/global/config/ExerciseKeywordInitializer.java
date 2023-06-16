package com.feedback.hafit.global.config;

import com.feedback.hafit.domain.goal.entity.ExerciseKeyword;
import com.feedback.hafit.domain.goal.repository.ExerciseKeywordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ExerciseKeywordInitializer implements ApplicationRunner {

    private final ExerciseKeywordRepository exerciseKeywordRepository;

    @Override
    public void run(ApplicationArguments args) {
        // 이미 초기화가 수행되었는지 확인
        if (exerciseKeywordRepository.count() > 0) {
            return; // 이미 초기화되었으므로 중단
        }

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

