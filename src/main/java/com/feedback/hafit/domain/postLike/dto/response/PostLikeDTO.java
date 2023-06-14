package com.feedback.hafit.domain.postLike.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostLikeDTO {

    private Long userId; // 유저 ID
    private Long postId; // 게시글 ID

    public PostLikeDTO(Long userId, Long postId) {
        this.userId = userId;
        this.postId = postId;
    }
}
