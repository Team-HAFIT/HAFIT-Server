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
public class CommentWithLikesDTO {

    @NotNull
    private Long commentId; // 댓글 ID
    private Long postId; // 게시글 ID
    private String commentContent; // 댓글 내용
    private String user_name; // 유저 ID
    private String user_imageUrl; // 프로필 사진
    private LocalDateTime createAt;
    private LocalDateTime modifiedAt;

    private Long commentTotalLikes;
    private boolean commentLikedByUser;

    public CommentWithLikesDTO(Comment comment, boolean commentLikedByUser, Long commentTotalLikes) {
        this.commentId = comment.getCommentId();
        this.user_imageUrl = comment.getUser().getImageUrl();
        this.postId = comment.getPost().getPostId();
        this.commentContent = comment.getComment_content();
        this.user_name = comment.getUser().getName();
        this.commentTotalLikes = commentTotalLikes;
        this.commentLikedByUser = commentLikedByUser;
        this.createAt = comment.getCreatedAt();
        this.modifiedAt = comment.getModifiedAt();
    }
}
