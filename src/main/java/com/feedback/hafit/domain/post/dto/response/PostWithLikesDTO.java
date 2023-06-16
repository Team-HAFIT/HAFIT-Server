package com.feedback.hafit.domain.post.dto.response;

import com.feedback.hafit.domain.post.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostWithLikesDTO {
    private LocalDateTime modifiedAt;
    private LocalDateTime createdAt;
    private Long postId;
    private String categoryName;
    private String userName;
    private String postContent;
    private List<PostFileDTO> files = Collections.emptyList();
    private Long postTotalLikes;
    private Long commentCount;
    private boolean postLikedByUser;

    // 게시글 전체 조회할 때 사용
    public PostWithLikesDTO(Post post, List<PostFileDTO> files, boolean postLikedByUser, Long commentCount, Long postTotalLikes) {
        this.postId = post.getPostId();
        this.categoryName = post.getCategory().getCategoryName();
        this.userName = post.getUser().getName();
        this.postContent = post.getPostContent();
        this.postTotalLikes = postTotalLikes;
        this.commentCount = commentCount;
        this.postLikedByUser = postLikedByUser;
        this.files = files;
        this.modifiedAt = post.getModifiedAt();
        this.createdAt = post.getCreatedAt();
    }

}