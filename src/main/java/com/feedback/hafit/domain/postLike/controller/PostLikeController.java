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
    public ResponseEntity<Boolean> insert(@PathVariable Long postId, Principal principal) throws Exception  {
        postLikeService.insert(postId, principal.getName());
        return ResponseEntity.ok(true);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Boolean> delete(@PathVariable Long postId, Principal principal) {
        log.info(String.valueOf(postId));
        boolean isPostLikeDeleted = postLikeService.delete(postId, principal.getName());
        if (!isPostLikeDeleted) {
            System.out.println("삭제 실패");
            return ResponseEntity.badRequest().body(false);
        }
        System.out.println("삭제 성공");
        return ResponseEntity.ok(true);
    }

}
