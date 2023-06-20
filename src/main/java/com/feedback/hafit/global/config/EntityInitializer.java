package com.feedback.hafit.global.config;

import com.feedback.hafit.domain.category.entity.Category;
import com.feedback.hafit.domain.category.repository.CategoryRepository;
import com.feedback.hafit.domain.goal.entity.Keyword;
import com.feedback.hafit.domain.goal.repository.KeywordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class EntityInitializer implements ApplicationRunner {

    private final KeywordRepository keywordRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public void run(ApplicationArguments args) {
        // 이미 초기화가 수행되었는지 확인
        if (keywordRepository.count() > 0 && categoryRepository.count() > 0) {
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
            Keyword exerciseKeyword = new Keyword(keyword);
            keywordRepository.save(exerciseKeyword);
        }

        List<String> initialCategories = Arrays.asList(
                "오운완",
                "자세 피드백",
                "운동 Q&A"
        );

        for (String category : initialCategories) {
            Category newCategory = new Category(category);
            categoryRepository.save(newCategory);
        }
    }
}
