package com.feedback.hafit.domain.comment.controller;

import com.feedback.hafit.domain.comment.dto.request.CommentCreateDTO;
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
    @PostMapping
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CommentDTO> create(@RequestBody CommentCreateDTO commentCreateDTO, Principal principal) {
        CommentDTO createdComment = commentService.write(commentCreateDTO, principal.getName());
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

//    @Operation(summary = "댓글 수정")
//    @PutMapping("/{id}")
//    @ResponseBody
//    @ResponseStatus(HttpStatus.OK)
//    public CommentDTO save(@RequestBody CommentUpdateDTO requestDto) {
//        return commentService.update(requestDto);
//    }
}
