package com.feedback.hafit.domain.goal.entity;

import com.feedback.hafit.domain.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "keywords")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExerciseKeyword extends BaseEntity {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    public Long keywordId;

    @Column(length = 300)
    private String keyword_name;

    public ExerciseKeyword(String keyword_name) {
        this.keyword_name = keyword_name;
    }

    public void setKeywordId(Long keywordId) {
        this.keywordId = keywordId;
    }

    public void setKeyword_name(String keyword_name) {
        this.keyword_name = keyword_name;
    }
}