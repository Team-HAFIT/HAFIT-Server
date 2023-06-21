package com.feedback.hafit.global.config;

import com.amazonaws.services.s3.AmazonS3;
import com.feedback.hafit.domain.category.entity.Category;
import com.feedback.hafit.domain.category.repository.CategoryRepository;
import com.feedback.hafit.domain.exercise.entity.Exercise;
import com.feedback.hafit.domain.exercise.entity.ExerciseKeyword;
import com.feedback.hafit.domain.exercise.repository.ExerciseKeywordRepository;
import com.feedback.hafit.domain.exercise.repository.ExerciseRepository;
import com.feedback.hafit.domain.goal.entity.Keyword;
import com.feedback.hafit.domain.goal.repository.KeywordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.*;

@Component
@RequiredArgsConstructor
public class EntityInitializer implements ApplicationRunner {

    private final KeywordRepository keywordRepository;
    private final CategoryRepository categoryRepository;
    private final ExerciseRepository exerciseRepository;
    private final ExerciseKeywordRepository exerciseKeywordRepository;

    private final AmazonS3 amazonS3;

    @Value("${cloud.aws.s3.bucket.directory-name:static}")
    private String S3_BUCKET_DIRECTORY_NAME = "static";

    String path = "https://feedback-file-bucket.s3.ap-northeast-2.amazonaws.com/"+S3_BUCKET_DIRECTORY_NAME + "/exercises/";
    @Override
    public void run(ApplicationArguments args) {
        // 이미 초기화가 수행되었는지 확인
        if (keywordRepository.count() > 0 && categoryRepository.count() > 0 && exerciseRepository.count()> 0) {
            return; // 이미 초기화되었으므로 중단
        }

        List<String> initialKeywords = Arrays.asList(
                "다이어트",
                "밸런스 있는 몸 만들기",
                "벌크업",
                "상체 강화",
                "하체 강화",
                "복근 만들기",
                "성취감 향상",
                "넓은 어깨 만들기",
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

        List<String> initialExercise = Arrays.asList(
                "스쿼트",
                "데드리프트",
                "벤치프레스",
                "플랭크",
                "오버헤드 프레스",
                "바벨로우",
                "바벨 컬",
                "트라이셉스 익스텐션",
                "런지",
                "레그 익스텐션",
                "레그 컬",
                "크런치",
                "레그 레이즈",
                "윗몸 일으키기",
                "풀업",
                "사이드 레터럴 레이즈",
                "프론트 레이즈"
        );

        List<String> initialExerciseContent = Arrays.asList(
                "스쿼트는 하체 근력을 향상시키는 데 도움이 되는 효과적인 운동입니다. 이 운동은 대퇴사두근, 대퇴이두근, 둔근, 햄스트링, 종아리 등 하체 근육을 강화하고 전신 균형과 안정성을 개선하는 데 도움이 됩니다.",
                "데드리프트는 전신 근력을 강화하는 운동 중 하나로, 주로 하체, 등, 허리 근육을 강화하는 데에 도움을 줍니다. 이 운동은 바벨이나 덤벨을 사용하여 수행됩니다.",
                "벤치프레스는 가슴, 어깨, 삼두근 등 상체 근육을 강화하는 대표적인 운동 중 하나입니다. 이 운동은 바벨 또는 덤벨을 사용하여 가슴을 푸시하는 동작을 수행합니다.",
                "플랭크는 전신 근력과 코어 근육을 강화하는 효과적인 운동 중 하나입니다. 이 운동은 복부, 등, 어깨, 팔 근육을 강화하고, 전체 균형과 안정성을 개선하는 데 도움이 됩니다.",
                "오버헤드 프레스는 어깨와 팔 근육을 강화하는 운동으로, 주로 바벨 또는 덤벨을 사용하여 수행됩니다. 이 운동은 어깨 근육의 크기와 강도를 향상시키고, 상체 균형과 안정성을 개선하는 데 도움이 됩니다.",
                "바벨로우는 등과 상방팔꿈치 근육을 주로 타겟으로 하는 운동입니다. 이 운동은 바벨을 사용하여 척추를 유지하면서 상방팔꿈치를 구부리고 뒤로 당기는 동작을 수행합니다.",
                "바벨 컬은 상완이두근(또는 이두근)을 주로 타겟으로 하는 운동입니다. 이 운동은 바벨을 사용하여 팔을 구부리는 동작을 수행합니다. 바벨 컬은 상완이두근의 근력을 향상시키고 상완이두근의 굵기와 형태를 개선하는 데 도움이 됩니다.",
                "트라이셉스 익스텐션은 상완삼두근(또는 삼두근)을 주로 타겟으로 하는 운동입니다. 이 운동은 상체 근력과 상완삼두근의 발달을 도와줍니다.",
                "런지는 하체 근력을 강화하는 운동으로, 대퇴사두근, 대둔근, 넙다리 근육 등 다양한 하체 근육을 타겟으로 합니다. 런지는 한 다리로 한 발 앞으로 나아가서 굴복한 자세를 취하고 다시 원래 자세로 돌아오는 동작을 수행합니다.",
                "레그 익스텐션은 대퇴사두근(사두근)을 강화하는 하체 운동입니다. 이 운동은 주로 레그 익스텐션 머신을 사용하여 수행되며, 앉은 자세에서 다리를 펴는 동작을 수행합니다.",
                "레그 컬은 대둔근과 넙다리 근육을 타겟으로 하는 하체 운동입니다. 주로 레그 컬 머신을 사용하여 수행되며, 뒤로 누워서 다리를 구부리는 동작을 수행합니다.",
                "크런치는 복부 근육을 주로 타겟으로 하는 운동 중 하나입니다. 이 운동은 복부의 전면 근육인 직경근과 외복사근을 강화하는 데에 효과적입니다.",
                "레그 레이즈는 복근과 허벅지 근육을 강화하는 운동입니다.",
                "윗몸 일으키기는 복부 근육을 강화하는 운동 중 하나입니다.",
                "풀업은 상체 근육을 강화하는 운동으로, 주로 등, 이두근, 어깨 근육을 발달시키는 데 효과적입니다.",
                "사이드 레터럴 레이즈는 어깨 근육을 강화하는 운동으로, 측면 어깨 근육을 특히 강조합니다.",
                "프론트 레이즈는 어깨 근육을 강화하는 운동 중 하나로, 전면 어깨 근육을 특히 강조합니다."
        );

        List<String> initialExerciseImg = Arrays.asList(
                path+"squat.png",
                path+"deadlift.png",
                path+"benchpress.png",
                path+"plank.png",
                path+"overheadpress.jpg",
                path+"barbellrow.jpg",
                path+"barbellcurls.png",
                path+"tricepsExtension.png",
                path+"rung.png",
                path+"legextension.png",
                path+"legcurl.jpg",
                path+"crunch.png",
                path+"legraise.png",
                path+"sitUp.png",
                path+"fullyUpgraded.jpg",
                path+"sideLateralRaise.png",
                path+"frontRaise.jpg"
        );

        for (int i = 0; i < initialExercise.size(); i++) {
            String exercise_name = initialExercise.get(i);
            String exercise_description = initialExerciseContent.get(i);
            String exercise_img = initialExerciseImg.get(i);

            Exercise newExercise = Exercise.builder()
                    .exerciseName(exercise_name)
                    .exerciseDescription(exercise_description)
                    .exerciseImg(exercise_img)
                    .build();
            exerciseRepository.save(newExercise);
        }

        // 키워드와 운동의 매핑 관계 정의
        Map<Long, Long[]> keywordExerciseMap = new HashMap<>();
        keywordExerciseMap.put(1L, new Long[]{1L, 2L, 9L, 15L, 12L, 14L});
        keywordExerciseMap.put(2L, new Long[]{2L, 1L, 4L});
        keywordExerciseMap.put(3L, new Long[]{5L, 3L, 2L, 1L, 6L});
        keywordExerciseMap.put(4L, new Long[]{3L, 5L, 7L, 8L});
        keywordExerciseMap.put(5L, new Long[]{1L, 9L, 10L, 11L});
        keywordExerciseMap.put(6L, new Long[]{12L, 4L, 13L, 14L});
        keywordExerciseMap.put(7L, new Long[]{15L, 16L, 17L});

        // 매핑된 키워드와 운동을 데이터베이스에 저장
        for (Map.Entry<Long, Long[]> entry : keywordExerciseMap.entrySet()) {
            Long keywordId = entry.getKey();
            Long[] exerciseIds = entry.getValue();

            Keyword keyword = keywordRepository.findById(keywordId)
                    .orElseThrow(() -> new EntityNotFoundException("Keyword not found with id: " + keywordId));


            for (Long exerciseId : exerciseIds) {
                Exercise exercise = exerciseRepository.findById(exerciseId)
                        .orElseThrow(() -> new EntityNotFoundException("Exercise not found with id: " + exerciseId));

                if (exercise != null) {
                    ExerciseKeyword exerciseKeyword = new ExerciseKeyword();
                    exerciseKeyword.setKeyword(keyword);
                    exerciseKeyword.setExercise(exercise);
                    exerciseKeywordRepository.save(exerciseKeyword);
                }
            }
        }
    }
}
