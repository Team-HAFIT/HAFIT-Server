package com.feedback.hafit.domain.post.dto.response;

import com.feedback.hafit.domain.comment.dto.response.CommentWithLikesDTO;
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
public class PostWithCommentsDTO {
    private LocalDateTime modifiedAt;
    private LocalDateTime createdAt;
    private Long postId;
    private Long categoryId;
    private String userName;
    private String postContent;
    private List<PostFileDTO> files = Collections.emptyList();
    private Long postTotalLikes;
    private Long commentCount;
    private boolean postLikedByUser;
    private List<CommentWithLikesDTO> comments = Collections.emptyList();

    // 게시글 1개 조회할 때 사용
    public PostWithCommentsDTO(Post post, List<PostFileDTO> files, boolean postLikedByUser, Long postTotalLikes, Long commentCount, List<CommentWithLikesDTO> comments) {
        this.postId = post.getPostId();
        this.categoryId = post.getCategory().getCategoryId();
        this.userName = post.getUser().getName();
        this.postContent = post.getPostContent();
        this.postTotalLikes = postTotalLikes;
        this.commentCount = commentCount;
        this.postLikedByUser = postLikedByUser;
        this.files = files;
        this.modifiedAt = post.getModifiedAt();
        this.createdAt = post.getCreatedAt();
        this.comments = comments;
    }

}