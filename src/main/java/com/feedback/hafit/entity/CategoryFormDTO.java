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
    private String category_name;

    public Category toEntity() {
        Category category = Category.builder()
                .category_name(category_name)
                .build();
        return category;
    }
    @Builder
    public CategoryFormDTO(String category_name) {
        this.category_name = category_name;
    }
}
