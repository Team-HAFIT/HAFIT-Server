package com.feedback.hafit.entity;


import lombok.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class CategoryFormDTO {
    @NotNull
    private Long category_id;
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