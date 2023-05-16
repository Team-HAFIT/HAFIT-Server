package com.feedback.hafit.controller;

import com.feedback.hafit.entity.PostFormDTO;
import com.feedback.hafit.repository.PostRepository;
import com.feedback.hafit.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
@CrossOrigin(origins = "#")
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostService postService;

    @PostMapping("/upload")   // 글쓰기
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

    @PostMapping("/update")
    @CrossOrigin(origins = "#")
    public boolean update(@RequestBody PostFormDTO postFormDTO) {
        boolean isPostUpdated = postService.update(postFormDTO);
        if(!isPostUpdated) {
            System.out.println("수정 실패");
            return false;
        }
        System.out.println("수정 성공");
        return true;
    }
}
