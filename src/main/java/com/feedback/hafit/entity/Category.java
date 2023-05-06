package com.feedback.hafit.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "category")
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본 키 매핑
    private Long category_id; // auto_increment

    @Column(unique = true, nullable = false, length = 30)
    private String category_name; // 카테고리 이름

    private @NotNull LocalDateTime created_at; // 카테고리 생성일 DB에만 저장

    @PrePersist // DB insert 되기 직전에 실행
    public void setCreated_at() {
        this.created_at = LocalDateTime.now();
    }

    @Builder
    public Category(String category_name, LocalDateTime created_at) {
        this.category_name = category_name;
        this.created_at = created_at;
    }

    public static Category createCategory(CategoryFormDTO categoryFormDTO) {
        Category category = Category.builder()
                .category_name(categoryFormDTO.getCategory_name())
                .build();
        return category;
    }
}