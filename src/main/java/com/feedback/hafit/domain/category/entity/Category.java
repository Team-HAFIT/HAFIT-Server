package com.feedback.hafit.domain.category.entity;

import com.feedback.hafit.domain.BaseEntity;
import com.feedback.hafit.domain.post.entity.Post;
import com.feedback.hafit.domain.user.entity.User;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@Table(name = "categories")
@NoArgsConstructor
@AllArgsConstructor
public class Category extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본 키 매핑
    private Long categoryId; // auto_increment

    @Column(unique = true, nullable = false, length = 30)
    private String categoryName; // 카테고리 이름

    @OneToMany(mappedBy = "post")
    private List<Post> posts;
    // category 1 -> N post 관계
}