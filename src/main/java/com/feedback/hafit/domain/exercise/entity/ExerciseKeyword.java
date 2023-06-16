package com.feedback.hafit.domain.exercise.entity;

import com.feedback.hafit.domain.keyword.entity.Keyword;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "ExerciseKeywords")
@AllArgsConstructor
@NoArgsConstructor
public class ExerciseKeyword { // 키워드_운동 연결

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "keyword_id", foreignKey = @ForeignKey(name = "fk_keyword"))
    private Keyword keywordId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exec_id", foreignKey = @ForeignKey(name = "fk_exec"))
    private Exercise execId;
}
