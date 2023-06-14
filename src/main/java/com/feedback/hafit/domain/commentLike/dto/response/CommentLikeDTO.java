package com.feedback.hafit.domain.commentLike.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentLikeDTO {
    private Long userId; // 유저 ID
    private Long commentId; // 게시글 ID

    public CommentLikeDTO(Long userId, Long commentId) {
        this.userId = userId;
        this.commentId = commentId;
    }
}
