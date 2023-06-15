package com.feedback.hafit.domain.comment.controller;

import com.feedback.hafit.domain.comment.dto.request.CommentCreateDTO;
import com.feedback.hafit.domain.comment.dto.request.CommentUpdateDTO;
import com.feedback.hafit.domain.comment.dto.response.CommentDTO;
import com.feedback.hafit.domain.comment.dto.response.CommentWithLikesDTO;
import com.feedback.hafit.domain.comment.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;

    @Operation(summary = "댓글 추가")
    @PostMapping("/{postId}")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CommentDTO> create(@PathVariable Long postId, @RequestBody CommentCreateDTO commentCreateDTO, Principal principal) {
        CommentDTO createdComment = commentService.write(postId, commentCreateDTO, principal.getName());
        return ResponseEntity.ok(createdComment);
    }

    @GetMapping // 전체 댓글 조회
    public ResponseEntity<List<CommentWithLikesDTO>> getAllComments(Principal principal) {
        List<CommentWithLikesDTO> commentDTOs = commentService.getAllComments(principal.getName());
        if (!commentDTOs.isEmpty()) {
            return ResponseEntity.ok(commentDTOs);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{commentId}") // 댓글 수정
    public ResponseEntity<CommentDTO> updateComment(@PathVariable Long commentId, @RequestBody CommentUpdateDTO commentUpdateDTO) {
        CommentDTO isCommentUpdated = commentService.update(commentId, commentUpdateDTO.getContent());
        return ResponseEntity.ok(isCommentUpdated);
    }

    @DeleteMapping("/{commentId}") // 댓글 삭제
    public ResponseEntity<Boolean> deletePost(@PathVariable Long commentId) {
        boolean isCommentDeleted = commentService.deleteById(commentId);
        if (!isCommentDeleted) {
            System.out.println("삭제 실패");
            return ResponseEntity.badRequest().body(false);
        }
        System.out.println("삭제 성공");
        return ResponseEntity.ok(true);
    }

}
