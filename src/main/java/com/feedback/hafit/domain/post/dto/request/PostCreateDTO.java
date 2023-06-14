package com.feedback.hafit.domain.post.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.List;

@Getter
@NoArgsConstructor
public class PostCreateDTO {
    private String userName;
    private Long categoryId;
    private String postContent;
    private List<MultipartFile> files = Collections.emptyList();
}