package com.feedback.hafit.domain.category.dto.request;


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
    private String category_name;
    private String userEmail;

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }
}