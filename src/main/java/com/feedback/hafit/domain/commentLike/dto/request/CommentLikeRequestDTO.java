package com.feedback.hafit.domain.commentLike.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentLikeRequestDTO {
    private Long userId; // 유저 ID
    private Long commentId; // 게시글 ID

    public CommentLikeRequestDTO(Long userId, Long commentId) {
        this.userId = userId;
        this.commentId = commentId;
    }
}
