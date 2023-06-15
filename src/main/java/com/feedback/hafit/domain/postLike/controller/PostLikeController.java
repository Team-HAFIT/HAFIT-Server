package com.feedback.hafit.domain.postLike.controller;

import com.feedback.hafit.domain.postLike.dto.request.PostLikeRequestDTO;
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

    @PostMapping
    public ResponseEntity<Boolean> insert(@RequestBody PostLikeRequestDTO postLikeRequestDTO, Principal principal) throws Exception  {
        postLikeService.insert(postLikeRequestDTO, principal.getName());
        return ResponseEntity.ok(true);
    }

    @DeleteMapping("/{post_like_Id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long post_like_Id, Principal principal) {
        boolean isPostLikeDeleted = postLikeService.delete(post_like_Id, principal.getName());
        if (!isPostLikeDeleted) {
            System.out.println("삭제 실패");
            return ResponseEntity.badRequest().body(false);
        }
        System.out.println("삭제 성공");
        return ResponseEntity.ok(true);
    }

}
