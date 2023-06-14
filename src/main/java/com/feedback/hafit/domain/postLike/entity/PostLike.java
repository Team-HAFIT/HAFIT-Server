package com.feedback.hafit.domain.postLike.entity;

import com.feedback.hafit.domain.BaseEntity;
import com.feedback.hafit.domain.post.entity.Post;
import com.feedback.hafit.domain.user.entity.User;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Getter
@Table(name = "post_likes")
@NoArgsConstructor
@AllArgsConstructor
public class PostLike extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long post_like_id; // 게시글 좋아요

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REMOVE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_post_like_user"))
    private User user; // 유저 ID

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "post_id", foreignKey = @ForeignKey(name = "fk_post_like_post"))
    private Post post; // 게시글 ID

}
