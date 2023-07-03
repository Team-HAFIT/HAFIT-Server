package com.feedback.hafit.domain.post.entity;

import com.feedback.hafit.domain.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "files")
@NoArgsConstructor
@AllArgsConstructor
public class PostFile extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fileId;

    @Column(name = "file_name")
    private String file_name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", foreignKey = @ForeignKey(name = "fk_file_post"))
    private Post post;

    @Builder
    public PostFile(String file_name, Post post) {
        this.file_name = file_name;
        this.post = post;
    }

}
