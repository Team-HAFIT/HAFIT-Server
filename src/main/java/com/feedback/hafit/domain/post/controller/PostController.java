package com.feedback.hafit.domain.post.controller;

import com.feedback.hafit.domain.comment.service.CommentService;
import com.feedback.hafit.domain.post.dto.request.PostCreateDTO;
import com.feedback.hafit.domain.post.dto.request.PostUpdateDTO;
import com.feedback.hafit.domain.post.dto.response.PostDTO;
import com.feedback.hafit.domain.post.dto.response.PostWithCommentsDTO;
import com.feedback.hafit.domain.post.dto.response.PostWithLikesDTO;
import com.feedback.hafit.domain.post.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
@Slf4j
public class PostController {

    private final PostService postService;
    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<PostDTO> createPost(@RequestParam("files") List<MultipartFile> files,
                                              @ModelAttribute PostCreateDTO postFormDTO,
                                              Principal principal) {
        PostDTO createdPost = postService.upload(postFormDTO, files, principal.getName());
        return ResponseEntity.ok(createdPost);
    }

    @PutMapping("/{postId}") // 피드 수정
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public void updatePost(@PathVariable Long postId,
                                              @RequestParam(value = "files", required = false) List<MultipartFile> files,
                                              @ModelAttribute PostUpdateDTO postFormDTO) {
        postService.update(postId, postFormDTO, files);
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

    @GetMapping // 전체 피드 조회
    public ResponseEntity<List<PostWithLikesDTO>> getAllPosts(Principal principal) {
        List<PostWithLikesDTO> postDTOs = postService.getAllPosts(principal.getName());
        if (!postDTOs.isEmpty()) {
            return ResponseEntity.ok(postDTOs);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
