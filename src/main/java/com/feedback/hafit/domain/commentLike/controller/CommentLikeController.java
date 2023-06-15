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
    public ResponseEntity<Boolean> insert(@PathVariable Long commentId, Principal principal) throws Exception  {
        commentLikeService.insert(commentId, principal.getName());
        return ResponseEntity.ok(true);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Boolean> delete(@PathVariable Long commentId, Principal principal) {
        commentLikeService.delete(commentId, principal.getName());
        return ResponseEntity.ok(true);
    }
}