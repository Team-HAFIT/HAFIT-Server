package com.feedback.hafit.domain.postLike.controller;

import com.feedback.hafit.domain.postLike.service.PostLikeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post-like")
public class PostLikeController {

    private final PostLikeService postLikeService;

    @PostMapping("/{postId}")
    public ResponseEntity<Boolean> insertPostLike(@PathVariable Long postId, Principal principal) {
        postLikeService.insertPostLike(postId, principal.getName());
        return ResponseEntity.ok(true);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Boolean> deletePostLike(@PathVariable Long postId, Principal principal) {
        postLikeService.deletePostLike(postId, principal.getName());
        return ResponseEntity.ok(true);
    }

}
