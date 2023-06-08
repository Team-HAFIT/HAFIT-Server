package com.feedback.hafit.domain.post.dto.reqeust;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PostUpdateDTO {
    private Long postId;
    private String userName;
    private Long categoryId;
    private String postContent;
    private List<MultipartFile> files = Collections.emptyList();
}