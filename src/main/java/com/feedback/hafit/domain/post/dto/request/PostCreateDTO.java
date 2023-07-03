package com.feedback.hafit.domain.post.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.List;

@Getter
@NoArgsConstructor
public class PostCreateDTO {
    private Long categoryId;
    private String user_name;
    private String post_content;
    private List<MultipartFile> files = Collections.emptyList();

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public void setPost_content(String post_content) {
        this.post_content = post_content;
    }

    public void setFiles(List<MultipartFile> files) {
        this.files = files;
    }
}