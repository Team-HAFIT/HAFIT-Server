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

    @Column(length = 11)
    private int hierarchy; // 계층, 대댓글

/*
    @Column(nullable = false, name = "category_id")
    private int category_id;

    @Column(nullable = false, name = "user_id")
    private int user_id;
*/

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_num")
    private Long user_num;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Long category_id;
    @Builder
    public Post(String post_content, LocalDateTime post_created, LocalDateTime post_modified, String post_img, int hierarchy, Long category_id, Long user_num) {
        this.post_content = post_content;
        this.post_created = post_created;
        this.post_modified = post_modified;
        this.post_img = post_img;
        this.hierarchy = hierarchy;
        this.category_id = category_id;
        this.user_num = user_num;
    }
    public PostDTO toPostDTO() {
        return PostDTO.builder()
                .post_content(this.post_content)
                .post_img(this.post_img)
                .build();
    }
}