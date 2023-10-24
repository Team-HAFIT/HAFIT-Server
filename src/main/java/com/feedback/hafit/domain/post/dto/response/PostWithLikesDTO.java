package com.feedback.hafit.domain.post.dto.response;

import com.feedback.hafit.domain.post.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostWithLikesDTO {
    private Long postId;
    private String category_name;
    private String user_name;
    private String email;
    private String imageUrl;
    private String post_content;
    private List<PostFileDTO> files = Collections.emptyList();
    private Long post_totalLikes;
    private Long comment_count;
    private boolean post_likedByUser;
    private String modifiedAt;
    private String createdAt;

    // 게시글 전체 조회할 때 사용
    public PostWithLikesDTO(Post post, List<PostFileDTO> files, boolean post_likedByUser, Long comment_count, Long post_totalLikes) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.postId = post.getPostId();
        this.category_name = post.getCategory().getCategory_name();
        this.user_name = post.getUser().getName();
        this.email = post.getUser().getEmail();
        this.imageUrl = post.getUser().getImageUrl();
        this.post_content = post.getPost_content();
        this.post_totalLikes = post_totalLikes;
        this.comment_count = comment_count;
        this.post_likedByUser = post_likedByUser;
        this.files = files;
        this.modifiedAt = post.getModifiedAt().format(formatter);
        this.createdAt = post.getCreatedAt().format(formatter);
    }

}
