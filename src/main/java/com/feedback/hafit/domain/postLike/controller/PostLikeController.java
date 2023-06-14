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

    @DeleteMapping
    public ResponseEntity<Boolean> delete(@RequestBody PostLikeRequestDTO postLikeRequestDTO, Principal principal) {
        postLikeService.delete(postLikeRequestDTO, principal.getName());
        return ResponseEntity.ok(true);
    }

}
