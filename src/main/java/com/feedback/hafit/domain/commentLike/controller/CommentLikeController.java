package com.feedback.hafit.domain.commentLike.controller;

import com.feedback.hafit.domain.commentLike.dto.request.CommentLikeRequestDTO;
import com.feedback.hafit.domain.commentLike.service.CommentLikeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/comment-like")
@RequiredArgsConstructor
@Slf4j
public class CommentLikeController {

    private final CommentLikeService commentLikeService;

    @PostMapping
    public ResponseEntity<Boolean> insert(@RequestBody CommentLikeRequestDTO commentLikeRequestDTO, Principal principal) throws Exception  {
        commentLikeService.insert(commentLikeRequestDTO, principal.getName());
        return ResponseEntity.ok(true);
    }

    @DeleteMapping
    public ResponseEntity<Boolean> delete(@RequestBody CommentLikeRequestDTO commentLikeRequestDTO, Principal principal) {
        commentLikeService.delete(commentLikeRequestDTO, principal.getName());
        return ResponseEntity.ok(true);
    }
}