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
    private String category_name;
    private String post_content;
    private List<PostFileDTO> files = Collections.emptyList();

    // [user] 좋아요 표시한 게시글, 작성한 게시글 조회에 사용
    public PostForUserDTO(Post post, List<PostFileDTO> files) {
        this.postId = post.getPostId();
        this.category_name = post.getCategory().getCategory_name();
        this.post_content = post.getPost_content();
        this.files = files;
    }
}
