package com.feedback.hafit.domain.post.controller;

import com.feedback.hafit.domain.post.dto.request.PostCreateDTO;
import com.feedback.hafit.domain.post.dto.request.PostUpdateDTO;
import com.feedback.hafit.domain.post.dto.response.PostDTO;
import com.feedback.hafit.domain.post.dto.response.PostWithCommentsDTO;
import com.feedback.hafit.domain.post.dto.response.PostWithLikesDTO;
import com.feedback.hafit.domain.post.service.PostService;
import com.feedback.hafit.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
@Slf4j
public class PostController {

    private final PostService postService;
    private final UserService userService;

    @PostMapping
    public ResponseEntity<PostDTO> createPost(@RequestParam("files") List<MultipartFile> files,
                                              @ModelAttribute PostCreateDTO postFormDTO,
                                              Principal principal) {
        log.info(String.valueOf(postFormDTO.getCategoryId()));
        PostDTO createdPost = postService.upload(postFormDTO, files, principal.getName());
        return ResponseEntity.ok(createdPost);
    }

    @PutMapping("/{postId}") // 피드 수정
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Boolean>  updatePost(@PathVariable Long postId,
                                              @RequestParam(value = "files", required = false) List<MultipartFile> files,
                                              @ModelAttribute PostUpdateDTO postFormDTO) {
        boolean isUpdated = postService.update(postId, postFormDTO, files);
        if (isUpdated) {
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.badRequest().body(false);
        }
    }

    @DeleteMapping("/{postId}") // 피드 삭제
    public ResponseEntity<Boolean> deletePost(@PathVariable Long postId) {
        boolean isPostDeleted = postService.deleteById(postId);
        if (isPostDeleted) {
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.badRequest().body(false);
        }
    }

    // 1개 게시글 조회
    @GetMapping("/{postId}")
    public ResponseEntity<PostWithCommentsDTO> getPostById(@PathVariable Long postId, Principal principal) {
        PostWithCommentsDTO postDTO = postService.getPostById(postId, principal.getName());
        if (postDTO != null) {
            return ResponseEntity.ok(postDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 전체 피드 조회
    @GetMapping
    public ResponseEntity<List<PostWithLikesDTO>> getAllPosts(Principal principal) {
        List<PostWithLikesDTO> postDTOs = postService.getAllPosts(principal.getName());
        if (!postDTOs.isEmpty()) {
            return ResponseEntity.ok(postDTOs);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 내가 작성한 글 조회
    @GetMapping("/my")
    public ResponseEntity<Map<String, Object>> getMyPosts(Principal principal) {
        try {
            String email = principal.getName();
            Map<String, Object> result = userService.getMyPosts(email);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    // 내가 좋아요 표시한 글 조회
    @GetMapping("/my/liked-posts")
    public ResponseEntity<Map<String, Object>> getLikedPostsByEmail(Principal principal) {
        try {
            String email = principal.getName();
            Map<String, Object> result = userService.getLikedPostsByEmail(email);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }
}
