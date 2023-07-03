package com.feedback.hafit.domain.category.entity;

import com.feedback.hafit.domain.BaseEntity;
import com.feedback.hafit.domain.post.entity.Post;
import com.feedback.hafit.domain.user.entity.User;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Builder
@Table(name = "categories")
@NoArgsConstructor
@AllArgsConstructor
public class Category extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본 키 매핑
    private Long categoryId; // auto_increment

    @Column(unique = true, nullable = false, length = 30)
    private String category_name; // 카테고리 이름

    @OneToMany(mappedBy = "category")
    private List<Post> posts;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_category_user"))
    private User user;

    public Category(String category_name) {
        this.category_name = category_name;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public void setUser(User user) {
        this.user = user;
    }
}