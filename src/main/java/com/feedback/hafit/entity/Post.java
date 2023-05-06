package com.feedback.hafit.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "post")
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long post_id;

    @Column(length = 8000)
    private String post_content;

    private @NotNull LocalDateTime post_created;

    private @NotNull LocalDateTime post_modified;

    @PrePersist
    public void setPost_created() {
        this.post_created = LocalDateTime.now();
    }

    @PreUpdate
    public void setPost_Modified() {
        this.post_modified = LocalDateTime.now();
    }

    @Column(length = 500)
    private String post_img;

    private int hierarchy; // 계층, 대댓글

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_num", foreignKey = @ForeignKey(name = "fk_post_user"))
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", foreignKey = @ForeignKey(name = "fk_post_category"))
    private Category category;

    @Builder
    public Post(String post_content, LocalDateTime post_created, LocalDateTime post_modified, String post_img, int hierarchy, User user, Category category) {
        this.post_content = post_content;
        this.post_created = post_created;
        this.post_modified = post_modified;
        this.post_img = post_img;
        this.hierarchy = hierarchy;
        this.user = user;
        this.category = category;
    }

    public PostDTO toPostDTO() {
        return PostDTO.builder()
                .post_content(this.post_content)
                .post_img(this.post_img)
                .build();
    }

}
