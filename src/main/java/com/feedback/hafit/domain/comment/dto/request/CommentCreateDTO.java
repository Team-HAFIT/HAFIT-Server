package com.feedback.hafit.domain.comment.dto.request;

import com.feedback.hafit.domain.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class CommentCreateDTO extends BaseEntity {
    @NotNull
    private Long commentId; // 댓글 ID
    private Long postId; // 게시글 ID
    private String content; // 댓글 내용
    private Long userId; // 유저 ID
}
