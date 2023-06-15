package com.feedback.hafit.domain.post.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostWithCountDTO {
    private PostForUserDTO postDTO;
    private Long count;

    // [user] 좋아요 표시한 게시글, 작성한 게시글 조회에 사용
    public PostWithCountDTO(PostForUserDTO postDTO, Long count) {
        this.postDTO = postDTO;
        this.count = count;
    }
}
