package com.feedback.hafit.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class CategoryFormDTO {
    @NotNull
    private String cat_name;

    public Category toEntity() {
        Category category = Category.builder()
                .cat_name(cat_name)
                .build();
        return category;
    }
    @Builder
    public CategoryFormDTO(String cat_name) {
        this.cat_name = cat_name;
    }
}
