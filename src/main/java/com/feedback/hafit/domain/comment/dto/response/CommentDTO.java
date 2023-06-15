package com.feedback.hafit.domain.comment.dto.response;

import com.feedback.hafit.domain.comment.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {
    @NotNull
    private Long commentId; // 댓글 ID
    private Long postId; // 게시글 ID
    private String content; // 댓글 내용
    private String userName; // 유저 ID
    private String imageUrl; // 프로필 사진
    private LocalDateTime createAt;
    private LocalDateTime modifiedAt;

    public CommentDTO(Comment comment) {
        this.commentId = comment.getCommentId();
        this.imageUrl = comment.getUser().getImageUrl();
        this.postId = comment.getPost().getPostId();
        this.content = comment.getContent();
        this.userName = comment.getUser().getName();
        this.createAt = comment.getCreatedAt();
        this.modifiedAt = comment.getModifiedAt();
    }

}

