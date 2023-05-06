/*
package com.feedback.hafit.controller;

import com.feedback.hafit.entity.PostFormDTO;
import com.feedback.hafit.repository.PostRepository;
import com.feedback.hafit.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostService postService;

    @PostMapping("/write")   // 글쓰기
    public boolean write(@RequestBody PostFormDTO postFormDTO) {
        boolean isPostCreated = postService.write(postFormDTO);
        if (!isPostCreated) {
            System.out.println("글 올리기 실패");
            return false;
        }
        System.out.println("글 올리기 성공");
        return true;
    }
}*/
