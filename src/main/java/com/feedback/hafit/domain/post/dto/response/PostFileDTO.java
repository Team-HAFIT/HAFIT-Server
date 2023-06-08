package com.feedback.hafit.domain.post.dto.response;

import com.feedback.hafit.domain.BaseEntity;
import com.feedback.hafit.domain.post.entity.PostFile;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostFileDTO extends BaseEntity {
    private Long imageId;

    private String fileName;

    public PostFileDTO(PostFile postFile){
        this.imageId = postFile.getImageId();
        this.fileName = postFile.getFileName();
        this.createdAt = postFile.getCreatedAt();
        this.modifiedAt = postFile.getModifiedAt();
    }
}
