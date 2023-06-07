package com.feedback.hafit.domain.post.dto.response;

import com.feedback.hafit.domain.BaseEntity;
import com.feedback.hafit.domain.category.entity.Category;
import com.feedback.hafit.domain.post.entity.FileImage;
import com.feedback.hafit.domain.post.entity.Post;
import com.feedback.hafit.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO extends BaseEntity {
    private Long postId;
    private Long categoryId;
    private String userEmail;
    private String postContent;
    private List<FileImageDTO> files = Collections.emptyList();

    public PostDTO(Post post, List<FileImageDTO> files) {
        this.postId = post.getPostId();
        this.categoryId = post.getCategory().getCategoryId();
        this.userEmail = post.getUser().getEmail();
        this.postContent = post.getPostContent();
        this.files = files;
        this.modifiedAt = post.getModifiedAt();
        this.createdAt = post.getCreatedAt();
    }

    public PostDTO(Long postId, List<FileImage> fileImages, String postContent, Category category, User user) {
        this.postId = postId;
        this.categoryId = category.getCategoryId();
        this.userEmail = user.getEmail();
        this.postContent = postContent;
        // Convert the list of FileImage objects to FileImageDTO objects
        this.files = fileImages.stream()
                .map(FileImageDTO::new)
                .collect(Collectors.toList());
    }
}