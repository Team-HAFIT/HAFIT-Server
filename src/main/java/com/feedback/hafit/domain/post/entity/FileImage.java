package com.feedback.hafit.domain.post.entity;

import com.feedback.hafit.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "files")
@NoArgsConstructor
@AllArgsConstructor
public class FileImage extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private String s3Key;

    @Column(name = "image_id")
    private Long imageId;

    @Column(name = "file_name")
    private String fileName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", foreignKey = @ForeignKey(name = "fk_file_post"))
    private Post post;

//    public void uploadToS3(FileImage fileImage, String bucketName) {
//        String key = generateUniqueKey(); // 고유한 키 생성 ( UUID.random )
//    }

}
