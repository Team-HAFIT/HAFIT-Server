package com.feedback.hafit.domain.post.dto.response;

import com.feedback.hafit.domain.BaseEntity;
import com.feedback.hafit.domain.post.entity.FileImage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FileImageDTO extends BaseEntity {
    private Long imageId;

    private String fileName;

    public FileImageDTO(FileImage fileImage){
        this.imageId = fileImage.getImageId();
        this.fileName = fileImage.getFileName();
        this.createdAt = fileImage.getCreatedAt();
        this.modifiedAt = fileImage.getModifiedAt();
    }
}
