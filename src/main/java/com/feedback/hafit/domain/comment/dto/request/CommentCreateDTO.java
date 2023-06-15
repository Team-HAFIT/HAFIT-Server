package com.feedback.hafit.domain.comment.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentCreateDTO {
    @NotNull
    private Long commentId; // 댓글 ID
    private Long postId; // 게시글 ID
    private String commentContent; // 댓글 내용
    private Long userId; // 유저 ID

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
