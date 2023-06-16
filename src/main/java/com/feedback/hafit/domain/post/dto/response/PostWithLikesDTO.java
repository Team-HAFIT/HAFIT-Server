package com.feedback.hafit.domain.post.dto.response;

import com.feedback.hafit.domain.post.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostWithLikesDTO {
    private Long postId;
    private String category_name;
    private String user_name;
    private String post_content;
    private List<PostFileDTO> files = Collections.emptyList();
    private Long post_totalLikes;
    private Long comment_count;
    private boolean post_likedByUser;
    private LocalDateTime modifiedAt;
    private LocalDateTime createdAt;

    // 게시글 전체 조회할 때 사용
    public PostWithLikesDTO(Post post, List<PostFileDTO> files, boolean post_likedByUser, Long comment_count, Long post_totalLikes) {
        this.postId = post.getPostId();
        this.category_name = post.getCategory().getCategory_name();
        this.user_name = post.getUser().getName();
        this.post_content = post.getPost_content();
        this.post_totalLikes = post_totalLikes;
        this.comment_count = comment_count;
        this.post_likedByUser = post_likedByUser;
        this.files = files;
        this.modifiedAt = post.getModifiedAt();
        this.createdAt = post.getCreatedAt();
    }

}