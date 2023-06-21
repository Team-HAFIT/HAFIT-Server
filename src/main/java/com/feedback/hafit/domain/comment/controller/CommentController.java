package com.feedback.hafit.domain.comment.controller;

import com.feedback.hafit.domain.comment.dto.request.CommentCreateDTO;
import com.feedback.hafit.domain.comment.dto.request.CommentUpdateDTO;
import com.feedback.hafit.domain.comment.dto.response.CommentWithLikesDTO;
import com.feedback.hafit.domain.comment.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;

    @Operation(summary = "댓글 추가")
    @PostMapping("/{postId}")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@PathVariable Long postId, @RequestBody CommentCreateDTO commentCreateDTO, Principal principal) {
        commentService.writeComment(postId, commentCreateDTO, principal.getName());
    }

    @GetMapping // 전체 댓글 조회
    public List<CommentWithLikesDTO> getAllComments(Principal principal) {
        return commentService.getAllComments(principal.getName());
    }

    @PutMapping("/{commentId}") // 댓글 수정
    public void updateComment(@PathVariable Long commentId, @RequestBody CommentUpdateDTO commentUpdateDTO) {
        commentService.update(commentId, commentUpdateDTO.getComment_content());
    }

    @DeleteMapping("/{commentId}") // 댓글 삭제
    public void deleteComment(@PathVariable Long commentId) {
        commentService.deleteById(commentId);
    }

    // 작성한 댓글 조회
    @GetMapping("/my")
    public Map<String, Object> getMyComments(Principal principal) {
        return commentService.getMyComments(principal.getName());
    }

}