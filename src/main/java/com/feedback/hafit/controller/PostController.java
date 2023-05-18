package com.feedback.hafit.controller;

import com.feedback.hafit.entity.Post;
import com.feedback.hafit.entity.PostFormDTO;
import com.feedback.hafit.repository.PostRepository;
import com.feedback.hafit.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

    @PostMapping("/upload") // 피드 등록
    @CrossOrigin(origins = "#")
    public PostFormDTO upload(@RequestBody PostFormDTO postFormDTO) {
        boolean isPostCreated = postService.upload(postFormDTO);
        if (!isPostCreated) {
            System.out.println("업로드 실패");
            return null; // 또는 실패 시에 적절한 응답을 반환할 수 있는 방법으로 변경
        }
        System.out.println("업로드 성공");
        return postFormDTO;
    }

    @PostMapping("/update") // 피드 수정
    @CrossOrigin(origins = "#")
    public PostFormDTO update(@RequestBody PostFormDTO postFormDTO) {
        boolean isPostUpdated = postService.update(postFormDTO);
        if (!isPostUpdated) {
            System.out.println("수정 실패");
            return null; // 또는 실패 시에 적절한 응답을 반환할 수 있는 방법으로 변경
        }
        System.out.println("수정 성공");
        return postFormDTO;
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
    public ResponseEntity<List<PostFormDTO>> getAllPosts() {
        List<Post> posts = postService.getAllPosts();
        List<PostFormDTO> postDTOs = new ArrayList<>();

        for (Post post : posts) {
            PostFormDTO postDTO = new PostFormDTO();
            postDTO.setPost_id(post.getPost_id());
            postDTO.setPost_content(post.getPost_content());
            postDTO.setPost_file(post.getPost_file());

            postDTOs.add(postDTO);
        }

        if (!postDTOs.isEmpty()) {
            return ResponseEntity.ok(postDTOs);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
