package com.feedback.hafit.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "posts")
@NoArgsConstructor
@AllArgsConstructor
public class Post extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long post_id;

    @Column(length = 8000)
    private String post_content;

    @Column(length = 500)
    private String post_file;

    private int hierarchy; // 계층, 대댓글

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_post_user"))
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", foreignKey = @ForeignKey(name = "fk_post_category"))
    private Category category;

    @Builder
    public Post(String post_content, String post_file, int hierarchy, User user, Category category) {
        this.post_content = post_content;
        this.post_file = post_file;
        this.hierarchy = hierarchy;
        this.user = user;
        this.category = category;
    }

    public PostDTO toPostDTO() {
        return PostDTO.builder()
                .post_content(this.post_content)
                .post_file(this.post_file)
                .build();
    }

}
