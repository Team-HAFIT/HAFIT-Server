package com.feedback.hafit.domain.post.controller;

import com.feedback.hafit.domain.post.dto.reqeust.PostCreateDTO;
import com.feedback.hafit.domain.post.dto.reqeust.PostUpdateDTO;
import com.feedback.hafit.domain.post.dto.response.PostDTO;
import com.feedback.hafit.domain.post.entity.Post;
import com.feedback.hafit.domain.post.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
@Slf4j
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<PostDTO> createPost(@RequestParam("files") List<MultipartFile> files,
                                              @ModelAttribute PostCreateDTO postFormDTO,
                                              Principal principal) {
        PostDTO createdPost = postService.upload(postFormDTO, files, principal.getName());
        return ResponseEntity.ok(createdPost);
    }

    @PutMapping("/{postId}") // 피드 수정
    public ResponseEntity<Boolean> updatePost(@PathVariable Long postId,
                                              @RequestParam(value = "files", required = false) List<MultipartFile> files,
                                              @ModelAttribute PostUpdateDTO postFormDTO) {
        boolean isPostUpdated = postService.update(postId, postFormDTO, files);
        if (!isPostUpdated) {
            System.out.println("수정 실패");
            return ResponseEntity.badRequest().body(false);
        }
        System.out.println("수정 성공");
        return ResponseEntity.ok(true);
    }

    @DeleteMapping("/{postId}") // 피드 삭제
    public ResponseEntity<Boolean> deletePost(@PathVariable Long postId) {
        boolean isPostDeleted = postService.deleteById(postId);
        if (!isPostDeleted) {
            System.out.println("삭제 실패");
            return ResponseEntity.badRequest().body(false);
        }
        System.out.println("삭제 성공");
        return ResponseEntity.ok(true);
    }

    @GetMapping // 전체 피드 조회
    public ResponseEntity<List<PostDTO>> getAllPosts() {
        List<Post> posts = postService.getAllPosts();
        if (!posts.isEmpty()) {
            List<PostDTO> postDTOs = posts.stream()
                    .map(post -> new PostDTO(
                            post.getPostId(),
                            post.getFileImages(),
                            post.getPostContent(),
                            post.getCategory(),
                            post.getUser()
                    ))
                    .collect(Collectors.toList());
            return ResponseEntity.ok(postDTOs);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
