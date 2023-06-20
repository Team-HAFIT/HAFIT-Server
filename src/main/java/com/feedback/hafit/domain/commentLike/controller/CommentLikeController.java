package com.feedback.hafit.domain.commentLike.controller;

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

    @PostMapping("/{commentId}")
    public ResponseEntity<Boolean> insertCommentLike(@PathVariable Long commentId, Principal principal) throws Exception  {
        commentLikeService.insertCommentLike(commentId, principal.getName());
        return ResponseEntity.ok(true);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Boolean> deleteCommentLike(@PathVariable Long commentId, Principal principal) {
        commentLikeService.deleteCommentLike(commentId, principal.getName());
        return ResponseEntity.ok(true);
    }
}