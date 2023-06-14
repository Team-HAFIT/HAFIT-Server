package com.feedback.hafit.domain.postLike.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostLikeRequestDTO {

    private Long userId; // 유저 ID
    private Long postId; // 게시글 ID

    public PostLikeRequestDTO(Long userId, Long postId) {
        this.userId = userId;
        this.postId = postId;
    }
}
