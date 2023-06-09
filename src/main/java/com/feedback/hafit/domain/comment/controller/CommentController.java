package com.feedback.hafit.domain.comment.controller;

import com.feedback.hafit.domain.comment.dto.request.CommentCreateDTO;
import com.feedback.hafit.domain.comment.dto.response.CommentDTO;
import com.feedback.hafit.domain.comment.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comment")
public class CommentController {

    private final CommentService commentService;

    @Operation(summary = "댓글 추가")
    @PostMapping
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public CommentDTO save(@RequestBody CommentCreateDTO commentCreateDTO, Principal principal) {
        return commentService.write(commentCreateDTO, principal.getName());
    }

//    @Operation(summary = "댓글 수정")
//    @PutMapping("/{id}")
//    @ResponseBody
//    @ResponseStatus(HttpStatus.OK)
//    public CommentDTO save(@RequestBody CommentUpdateDTO requestDto) {
//        return commentService.update(requestDto);
//    }
}
