package com.feedback.hafit.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDTO {
    private Long cat_num;

    private String cat_name;

    private LocalDateTime cat_created;

    public Category toEntity() {
        Category category = Category.builder()
                .cat_name(cat_name)
                .cat_created(cat_created)
                .build();
        return category;
    }

    @Builder
    public CategoryDTO(Long cat_num, String cat_name, LocalDateTime cat_created) {
        this.cat_num = cat_num;
        this.cat_name = cat_name;
        this.cat_created = cat_created;
    }
}
