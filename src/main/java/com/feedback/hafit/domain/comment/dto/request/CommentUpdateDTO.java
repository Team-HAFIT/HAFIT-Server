package com.feedback.hafit.domain.comment.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class CommentUpdateDTO {
    @NotNull
    private Long postId; // 게시글 ID
    private String content; // 댓글 내용
    private String name; // 이름
    private Long CommentId; // 댓글 ID
}
