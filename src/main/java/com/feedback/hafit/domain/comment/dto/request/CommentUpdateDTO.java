package com.feedback.hafit.domain.comment.dto.request;

import com.feedback.hafit.domain.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter

@Builder
public class CommentUpdateDTO extends BaseEntity {
    @NotNull
    private Long postId; // 게시글 ID
    private String content; // 댓글 내용
    private String name; // 이름
    private Long CommentId; // 댓글 ID
}
