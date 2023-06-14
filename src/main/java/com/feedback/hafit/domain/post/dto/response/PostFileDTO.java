package com.feedback.hafit.domain.post.dto.response;

import com.feedback.hafit.domain.post.entity.PostFile;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostFileDTO{
    private Long imageId;

    private String fileName;
    private LocalDateTime modifiedAt;
    private LocalDateTime createdAt;

    public PostFileDTO(PostFile postFile){
        this.imageId = postFile.getImageId();
        this.fileName = postFile.getFileName();
        this.createdAt = postFile.getCreatedAt();
        this.modifiedAt = postFile.getModifiedAt();
    }
}
