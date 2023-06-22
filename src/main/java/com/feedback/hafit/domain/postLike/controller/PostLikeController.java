package com.feedback.hafit.domain.postLike.controller;

import com.feedback.hafit.domain.postLike.service.PostLikeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts/like")
public class PostLikeController {

    private final PostLikeService postLikeService;

    @PostMapping("/{postId}")
    public void insertPostLike(@PathVariable Long postId, Principal principal) {
        postLikeService.insertPostLike(postId, principal.getName());
    }

    @DeleteMapping("/{postId}")
    public void deletePostLike(@PathVariable Long postId, Principal principal) {
        postLikeService.deletePostLike(postId, principal.getName());
    }

}
