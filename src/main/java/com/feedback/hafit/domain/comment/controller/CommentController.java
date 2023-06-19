package com.feedback.hafit.domain.comment.controller;

import com.feedback.hafit.domain.comment.dto.request.CommentCreateDTO;
import com.feedback.hafit.domain.comment.dto.request.CommentUpdateDTO;
import com.feedback.hafit.domain.comment.dto.response.CommentWithLikesDTO;
import com.feedback.hafit.domain.comment.service.CommentService;
import com.feedback.hafit.domain.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;
    private final UserService userService;

    @Operation(summary = "댓글 추가")
    @PostMapping("/{postId}")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Boolean> create(@PathVariable Long postId, @RequestBody CommentCreateDTO commentCreateDTO, Principal principal) {
         commentService.writeComment(postId, commentCreateDTO, principal.getName());
            return ResponseEntity.ok(true);
    }

    @GetMapping // 전체 댓글 조회
    public ResponseEntity<List<CommentWithLikesDTO>> getAllComments(Principal principal) {
        List<CommentWithLikesDTO> commentDTOs = commentService.getAllComments(principal.getName());
            return ResponseEntity.ok(commentDTOs);
    }

    @PutMapping("/{commentId}") // 댓글 수정
    public ResponseEntity<Boolean> updateComment(@PathVariable Long commentId, @RequestBody CommentUpdateDTO commentUpdateDTO) {
        commentService.update(commentId, commentUpdateDTO.getComment_content());
            return ResponseEntity.ok(true);
    }

    @DeleteMapping("/{commentId}") // 댓글 삭제
    public ResponseEntity<Boolean> deleteComment(@PathVariable Long commentId) {
         commentService.deleteById(commentId);
            return ResponseEntity.ok(true);
    }

    // 작성한 댓글 조회
    @GetMapping("/my")
    public ResponseEntity<Map<String, Object>> getMyComments(Principal principal) {
            String email = principal.getName();
            Map<String, Object> result = userService.getMyComments(email);
            return ResponseEntity.ok(result);
    }

}
