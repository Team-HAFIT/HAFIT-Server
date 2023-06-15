package com.feedback.hafit.domain.comment.dto.response;

import com.feedback.hafit.domain.comment.entity.Comment;
import com.feedback.hafit.domain.post.dto.response.PostFileDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentForUserDTO {
    @NotNull
    private Long commentId; // 댓글 ID
    private Long postId; // 게시글 ID
    private String postContent;
    private String commentContent; // 댓글 내용
    private List<PostFileDTO> files = Collections.emptyList();
    private String userName; // 유저 ID
    private String imageUrl; // 프로필 사진
    private LocalDateTime createAt;
    private LocalDateTime modifiedAt;

    // [user] 작성한 댓글 조회에 사용
    public CommentForUserDTO(Comment comment, List<PostFileDTO> files) {
        this.commentId = comment.getCommentId();
        this.imageUrl = comment.getUser().getImageUrl();
        this.postId = comment.getPost().getPostId();
        this.postContent = comment.getPost().getPostContent();
        this.commentContent = comment.getContent();
        this.userName = comment.getUser().getName();
        this.createAt = comment.getCreatedAt();
        this.modifiedAt = comment.getModifiedAt();
        this.files = files;
    }
}
