package com.feedback.hafit.domain.commentLike.entity;

import com.feedback.hafit.domain.BaseEntity;
import com.feedback.hafit.domain.comment.entity.Comment;
import com.feedback.hafit.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "comment_likes")
@NoArgsConstructor
@AllArgsConstructor
public class CommentLike extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long comment_like_id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REMOVE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_comment_like_user"))
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "comment_id", foreignKey = @ForeignKey(name = "fk_comment_like_comment"))
    private Comment comment;
}