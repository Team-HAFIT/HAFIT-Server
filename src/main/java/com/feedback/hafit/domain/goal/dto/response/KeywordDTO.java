package com.feedback.hafit.domain.goal.dto.response;

import com.feedback.hafit.domain.goal.entity.Keyword;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class KeywordDTO {

    public Long keywordId;

    private String keyword_name;

    public KeywordDTO(Keyword keyword) {
        this.keywordId = keyword.getKeywordId();
        this.keyword_name = keyword.getKeyword_name();
    }
}
