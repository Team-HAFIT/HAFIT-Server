package com.feedback.hafit.domain.post.controller;

import com.feedback.hafit.domain.post.entity.Post;
import com.feedback.hafit.domain.post.dto.PostDTO;
import com.feedback.hafit.domain.post.repository.PostRepository;
import com.feedback.hafit.domain.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostService postService;

    @PostMapping("/cat")
    public boolean ca( HttpSession session) {
        session.setAttribute("categoryId", "1");
        System.out.println(session.getAttribute("categoryId"));
        System.out.println(session.getAttribute("categoryId"));
        System.out.println(session.getAttribute("categoryId"));
        System.out.println(session.getAttribute("categoryId"));
        System.out.println(session.getAttribute("categoryId"));
        System.out.println(session.getAttribute("categoryId"));
        System.out.println(session.getAttribute("categoryId"));
        return true;
    }
    @PostMapping("/upload")   // 피드 등록
    public boolean upload(@RequestBody PostDTO postFormDTO, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        // TODO categoryId 세션 값 확인
//        Long categoryId = (Long) session.getAttribute("categoryId");
        Long categoryId = 1L;
        boolean isPostCreated = postService.upload(postFormDTO, userId, categoryId);
        if (!isPostCreated) {
            System.out.println("업로드 실패");
            return false;
        }
        System.out.println("업로드 성공");
        return true;
    }

    @PostMapping("/update") // 피드 수정
    public boolean update(@RequestBody PostDTO postFormDTO) {
        boolean isPostUpdated = postService.update(postFormDTO);
        if (!isPostUpdated) {
            System.out.println("수정 실패");
            return false;
        }
        System.out.println("수정 성공");
        return true;
    }

    @DeleteMapping("/delete") // 피드 삭제
    public boolean delete(@RequestBody PostDTO postFormDTO) {
        boolean isPostDeleted = postService.delete(postFormDTO);
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