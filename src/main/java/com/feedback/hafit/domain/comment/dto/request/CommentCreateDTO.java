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
    private Long userId; // 유저 ID
    private String comment_content; // 댓글 내용

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public void setComment_content(String comment_content) {
        this.comment_content = comment_content;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
