package com.feedback.hafit.domain.post.entity;

import com.feedback.hafit.domain.BaseEntity;
import com.feedback.hafit.domain.category.entity.Category;
import com.feedback.hafit.domain.comment.entity.Comment;
import com.feedback.hafit.domain.postLike.entity.PostLike;
import com.feedback.hafit.domain.user.entity.User;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "posts")
@NoArgsConstructor
@AllArgsConstructor
public class Post extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @Column(length = 8000)
    private String postContent;

    @OneToMany(mappedBy = "post", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, orphanRemoval = true)
    private List<PostFile> postFiles = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, orphanRemoval = true)
    private List<PostLike> postLikes = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_post_user"))
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "category_id", foreignKey = @ForeignKey(name = "fk_post_category"))
    private Category category;

    @Builder
    public Post(String postContent, User user, Category category) {
        this.postContent = postContent;
        this.user = user;
        this.category = category;
    }

    public void update(
            String postContent,
            Category category
    ) {
        if (postContent != null) this.postContent = postContent;
        if (category != null) this.category = category;
    }
}
