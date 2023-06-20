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
    private String comment_content; // 댓글 내용
    private String user_name; // 유저 ID
    private String user_imageUrl; // 프로필 사진
    private LocalDateTime createAt;
    private LocalDateTime modifiedAt;
    private String user_LastCommentTime;
    private Long comment_totalLikes;
    private boolean comment_likedByUser;

    public CommentWithLikesDTO(Comment comment, boolean comment_likedByUser, Long comment_totalLikes) {
        this.commentId = comment.getCommentId();
        this.user_imageUrl = comment.getUser().getImageUrl();
        this.postId = comment.getPost().getPostId();
        this.comment_content = comment.getComment_content();
        this.user_name = comment.getUser().getName();
        this.comment_totalLikes = comment_totalLikes;
        this.comment_likedByUser = comment_likedByUser;
        this.createAt = comment.getCreatedAt();
        this.modifiedAt = comment.getModifiedAt();
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public void setComment_content(String comment_content) {
        this.comment_content = comment_content;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public void setUser_imageUrl(String user_imageUrl) {
        this.user_imageUrl = user_imageUrl;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public void setModifiedAt(LocalDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public void setComment_totalLikes(Long comment_totalLikes) {
        this.comment_totalLikes = comment_totalLikes;
    }

    public void setComment_likedByUser(boolean comment_likedByUser) {
        this.comment_likedByUser = comment_likedByUser;
    }

    public void setUser_lastCommentTime(String user_LastCommentTime) {
        this.user_LastCommentTime = user_LastCommentTime;
    }
}
