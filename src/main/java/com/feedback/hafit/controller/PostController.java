package com.feedback.hafit.controller;

import com.feedback.hafit.entity.Post;
import com.feedback.hafit.entity.PostFormDTO;
import com.feedback.hafit.repository.PostRepository;
import com.feedback.hafit.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
@CrossOrigin(origins = "#")
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostService postService;

    @PostMapping("/upload")   // 피드 등록
    @CrossOrigin(origins = "#")
    public boolean upload(@RequestBody PostFormDTO postFormDTO) {
        boolean isPostCreated = postService.upload(postFormDTO);
        if (!isPostCreated) {
            System.out.println("업로드 실패");
            return false;
        }
        System.out.println("업로드 성공");
        return true;
    }

    @PostMapping("/update") // 피드 수정
    @CrossOrigin(origins = "#")
    public boolean update(@RequestBody PostFormDTO postFormDTO) {
        boolean isPostUpdated = postService.update(postFormDTO);
        if (!isPostUpdated) {
            System.out.println("수정 실패");
            return false;
        }
        System.out.println("수정 성공");
        return true;
    }

    @DeleteMapping("/delete") // 피드 삭제
    @CrossOrigin(origins = "#")
    public boolean delete(@RequestBody PostFormDTO postFormDTO) {
        boolean isPostDeleted = postService.delete(postFormDTO);
        if (!isPostDeleted) {
            System.out.println("삭제 실패");
            return false;
        }
        System.out.println("삭제 성공");
        return true;
    }

    @GetMapping("/list") // 전체 피드 조회
    @CrossOrigin(origins = "#")
    public ResponseEntity<List<Post>> getAllPosts() {
        List<Post> posts = postService.getAllPosts();
        if (!posts.isEmpty()) {
            return ResponseEntity.ok(posts);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
