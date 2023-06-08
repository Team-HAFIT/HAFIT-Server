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
    @Column(name = "image_id")
    private Long imageId;

    @Column(name = "file_name")
    private String fileName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", foreignKey = @ForeignKey(name = "fk_file_post"))
    private Post post;

    @Builder
    public PostFile(String fileName, Post post) {
        this.fileName = fileName;
        this.post = post;
    }

}
