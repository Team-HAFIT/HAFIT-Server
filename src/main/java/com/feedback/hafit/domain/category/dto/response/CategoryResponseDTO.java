package com.feedback.hafit.domain.category.dto.response;


import com.feedback.hafit.domain.category.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponseDTO {

    private Long categoryId;
    private String category_name;

    public CategoryResponseDTO(Category category) {
        this.categoryId = category.getCategoryId();
        this.category_name = category.getCategory_name();
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }
}