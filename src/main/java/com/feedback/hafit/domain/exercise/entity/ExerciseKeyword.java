package com.feedback.hafit.domain.exercise.entity;

import com.feedback.hafit.domain.goal.entity.Keyword;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "exercise_keywords")
@AllArgsConstructor
@NoArgsConstructor
public class ExerciseKeyword { // 키워드_운동 연결

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long exercise_keywords_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "keyword_id", foreignKey = @ForeignKey(name = "fk_exercise_keyword_keyword"))
    private Keyword keyword;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exercise_id", foreignKey = @ForeignKey(name = "fk_exercise_keyword_exercise"))
    private Exercise exercise;

    public void setExercise_keywords_id(Long exercise_keywords_id) {
        this.exercise_keywords_id = exercise_keywords_id;
    }

    public void setKeyword(Keyword keyword) {
        this.keyword = keyword;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }
}