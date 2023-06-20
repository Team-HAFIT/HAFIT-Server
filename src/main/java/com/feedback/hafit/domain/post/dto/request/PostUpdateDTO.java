package com.feedback.hafit.domain.post.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.List;

@Getter
@NoArgsConstructor
public class PostUpdateDTO {
    private Long categoryId;
    private String post_content;
    private List<Long> deleteImageIds;
    private List<MultipartFile> files = Collections.emptyList();

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public void setPostContent(String post_content) {
        this.post_content = post_content;
    }

    public void setDeleteImageIds(List<Long> deleteImageIds) {
        this.deleteImageIds = deleteImageIds;
    }

    public void setFiles(List<MultipartFile> files) {
        this.files = files;
    }
}