package com.feedback.hafit.domain.category.dto.response;


import com.feedback.hafit.domain.category.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponseDTO {

    private String categoryName;

    public CategoryResponseDTO(Category category) {
        this.categoryName = category.getCategoryName();
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}