package com.feedback.hafit.domain.post.dto.response;

import com.feedback.hafit.domain.comment.dto.response.CommentWithLikesDTO;
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
public class PostWithCommentsDTO {
    private Long postId;
    private Long categoryId;
    private String category_name;
    private String user_name;
    private String post_content;
    private List<PostFileDTO> files = Collections.emptyList();
    private Long post_totalLikes;
    private Long comment_count;
    private boolean post_likedByUser;
    private List<CommentWithLikesDTO> comments = Collections.emptyList();
    private String modifiedAt;
    private String createdAt;

    // 게시글 1개 조회할 때 사용
    public PostWithCommentsDTO(Post post, List<PostFileDTO> files, boolean post_likedByUser, Long post_totalLikes, Long comment_count, List<CommentWithLikesDTO> comments) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.postId = post.getPostId();
        this.categoryId = post.getCategory().getCategoryId();
        this.category_name = post.getCategory().getCategory_name();
        this.user_name = post.getUser().getName();
        this.post_content = post.getPost_content();
        this.post_totalLikes = post_totalLikes;
        this.comment_count = comment_count;
        this.post_likedByUser = post_likedByUser;
        this.files = files;
        this.modifiedAt = post.getModifiedAt().format(formatter);
        this.createdAt = post.getCreatedAt().format(formatter);
        this.comments = comments;
    }

}