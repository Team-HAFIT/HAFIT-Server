package com.feedback.hafit.domain.commentLike.controller;

import com.feedback.hafit.domain.commentLike.service.CommentLikeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/comment-like")
@RequiredArgsConstructor
@Slf4j
public class CommentLikeController {

    private final CommentLikeService commentLikeService;

    @PostMapping("/{commentId}")
    public void insertCommentLike(@PathVariable Long commentId, Principal principal) throws Exception  {
        commentLikeService.insertCommentLike(commentId, principal.getName());
    }

    @DeleteMapping("/{commentId}")
    public void deleteCommentLike(@PathVariable Long commentId, Principal principal) {
        commentLikeService.deleteCommentLike(commentId, principal.getName());
    }
}