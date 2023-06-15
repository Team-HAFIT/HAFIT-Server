package com.feedback.hafit.domain.post.dto.response;

import com.feedback.hafit.domain.post.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostForUserDTO {
    private Long postId;
    private String categoryName;
    private String postContent;
    private List<PostFileDTO> files = Collections.emptyList();

    // [user] 좋아요 표시한 게시글, 작성한 게시글 조회에 사용
    public PostForUserDTO(Post post, List<PostFileDTO> files) {
        this.postId = post.getPostId();
        this.categoryName = post.getCategory().getCategoryName();
        this.postContent = post.getPostContent();
        this.files = files;
    }
}
