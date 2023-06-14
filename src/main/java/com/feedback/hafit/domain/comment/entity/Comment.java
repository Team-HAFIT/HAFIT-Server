package com.feedback.hafit.domain.comment.entity;

import com.feedback.hafit.domain.BaseEntity;
import com.feedback.hafit.domain.post.entity.Post;
import com.feedback.hafit.domain.user.entity.User;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
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

    @ManyToOne(fetch = FetchType.LAZY) // user ID
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_comment_user"))
    private User user;

    @ManyToOne(fetch = FetchType.LAZY) // 게시글 ID
    @JoinColumn(name = "post_id", foreignKey = @ForeignKey(name = "fk_comment_post"))
    private Post post;

}
