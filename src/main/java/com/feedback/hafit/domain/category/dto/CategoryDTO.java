package com.feedback.hafit.domain.category.dto;


import com.feedback.hafit.domain.category.entity.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDTO {

    @NotNull
    private Long categoryId;
    private String categoryName;
    private String userEmail;

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public CategoryDTO(Category category) {
        this.categoryId = category.getCategoryId();
        this.categoryName = category.getCategoryName();
        this.userEmail = category.getUser().getEmail();
    }

}