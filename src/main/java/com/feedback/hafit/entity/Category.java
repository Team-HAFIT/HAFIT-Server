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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cat_num; // auto_increment

    @Column(nullable = false, length = 30)
    private String cat_name; // 카테고리 이름

    private @NotNull LocalDateTime cat_created; // 카테고리 생성일 DB에만 저장

    @PrePersist // DB insert 되기 직전에 실행
    public void setCat_created() {
        this.cat_created = LocalDateTime.now();
    }

    @Builder
    public Category(String cat_name, LocalDateTime cat_created) {
        this.cat_name = cat_name;
        this.cat_created = cat_created;
    }

    public static Category createCategory(CategoryFormDTO categoryFormDTO) {
        Category category = Category.builder()
                .cat_name(categoryFormDTO.getCat_name())
                .build();
        return category;
    }
}
