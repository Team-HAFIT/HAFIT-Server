package com.feedback.hafit.domain.comment.dto.response;

import com.feedback.hafit.domain.comment.entity.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class CommentWithLikesDTO {

    @NotNull
    private Long commentId; // 댓글 ID
    private Long postId; // 게시글 ID
    private String content; // 댓글 내용
    private String userName; // 유저 ID
    private String imageUrl; // 프로필 사진
    private LocalDateTime createAt;
    private LocalDateTime modifiedAt;

    private Long commentTotalLikes;
    private boolean commentLikedByUser;

    public CommentWithLikesDTO(Comment comment, boolean commentLikedByUser, Long commentTotalLikes) {
        this.commentId = comment.getCommentId();
        this.imageUrl = comment.getUser().getImageUrl();
        this.postId = comment.getPost().getPostId();
        this.content = comment.getContent();
        this.userName = comment.getUser().getName();
        this.commentTotalLikes = commentTotalLikes;
        this.commentLikedByUser = commentLikedByUser;
        this.createAt = comment.getCreatedAt();
        this.modifiedAt = comment.getModifiedAt();
    }
}
