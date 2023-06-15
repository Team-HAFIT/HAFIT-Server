package com.feedback.hafit.domain.category.dto.request;


import com.feedback.hafit.domain.category.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryRequestDTO {

    @NotNull
    private Long categoryId;
    private String categoryName;
    private String userEmail;

    public CategoryRequestDTO(Category category) {
        this.categoryId = category.getCategoryId();
        this.categoryName = category.getCategoryName();
        this.userEmail = category.getUser().getEmail();
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}