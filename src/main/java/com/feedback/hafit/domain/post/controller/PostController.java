package com.feedback.hafit.domain.post.controller;

import com.feedback.hafit.domain.post.dto.reqeust.PostCreateDTO;
import com.feedback.hafit.domain.post.dto.reqeust.PostUpdateDTO;
import com.feedback.hafit.domain.post.dto.response.PostDTO;
import com.feedback.hafit.domain.post.entity.Post;
import com.feedback.hafit.domain.post.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
@Slf4j
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("")   // 피드 등록
    public PostDTO upload(PostCreateDTO postFormDTO) {

        postFormDTO.getFiles().forEach(file ->
                log.info(file.getOriginalFilename())
        );
        return postService.upload(postFormDTO);
    }

    @PutMapping("") // 피드 수정
    public boolean update(@RequestBody PostUpdateDTO postFormDTO) {
        boolean isPostUpdated = postService.update(postFormDTO);
        if (!isPostUpdated) {
            System.out.println("수정 실패");
            return false;
        }
        System.out.println("수정 성공");
        return true;
    }

    @DeleteMapping("/{postId}") // 피드 삭제
    public boolean delete(@PathVariable Long postId) {
        boolean isPostDeleted = postService.deleteById(postId);
        if (!isPostDeleted) {
            System.out.println("삭제 실패");
            return false;
        }
        System.out.println("삭제 성공");
        return true;
    }

    @GetMapping("/list") // 전체 피드 조회
    public ResponseEntity<List<Post>> getAllPosts() {
        List<Post> posts = postService.getAllPosts();
        if (!posts.isEmpty()) {
            return ResponseEntity.ok(posts);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
