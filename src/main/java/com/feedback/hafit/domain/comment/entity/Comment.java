package com.feedback.hafit.domain.comment.entity;

import com.feedback.hafit.domain.BaseEntity;
import com.feedback.hafit.domain.post.entity.Post;
import com.feedback.hafit.domain.user.entity.User;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "comments")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long commentId; // 댓글 ID

    @Column(name = "content", nullable = false)
    private String content; // 댓글 내용

    @ManyToMany
    @JoinTable(
            name = "comment_likes",
            joinColumns = @JoinColumn(name = "comment_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )

    private Set<User> likedUsers = new HashSet<>(); // 좋아요를 누른 사용자들

    @ManyToOne(fetch = FetchType.LAZY) // user ID
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_comment_user"))
    private User user;

    @ManyToOne(fetch = FetchType.LAZY) // 게시글 ID
    @JoinColumn(name = "post_id", foreignKey = @ForeignKey(name = "fk_comment_post"))
    private Post post;


    public Comment(User user, String content, Post post) {
        this.user = user;
        this.post = post;
        this.content = content;
    }

    // 댓글 좋아요 로직
    public void addLike(User user) {
        likedUsers.add(user);
    }

    public void removeLike(User user) {
        likedUsers.remove(user);
    }

    public int getLikesCount() {
        return likedUsers.size();
    }
}
