package com.feedback.hafit.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@Table(name = "post")
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long post_id;

    @Column(nullable = false, length = 8000)
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

    private String hierarchy; // 계층, 대댓글

    @Column(name = "category_id")
    private int category_id;

    @Column(name = "user_id")
    private int user_id;
}
