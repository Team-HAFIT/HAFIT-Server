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
    private Long category_id;

    private String category_name;

    private LocalDateTime created_at;

    public Category toEntity() {
        Category category = Category.builder()
                .category_name(category_name)
                .created_at(created_at)
                .build();
        return category;
    }

    @Builder
    public CategoryDTO(Long category_id, String category_name, LocalDateTime created_at) {
        this.category_id = getCategory_id();
        this.category_name = getCategory_name();
        this.created_at = created_at;
    }
}
