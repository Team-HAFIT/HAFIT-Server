package com.feedback.hafit.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "categorys")
@NoArgsConstructor
@AllArgsConstructor
public class Category extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본 키 매핑
    private Long category_id; // auto_increment

    @Column(unique = true, nullable = false, length = 30)
    private String category_name; // 카테고리 이름

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Post> posts;

    @Builder
    public Category(String category_name) {
        this.category_name = category_name;
    }

    public static Category createCategory(CategoryFormDTO categoryFormDTO) {
        Category category = Category.builder()
                .category_name(categoryFormDTO.getCategory_name())
                .build();
        return category;
    }
}