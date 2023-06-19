package com.feedback.hafit.domain.post.controller;

import com.feedback.hafit.domain.post.dto.request.PostCreateDTO;
import com.feedback.hafit.domain.post.dto.request.PostUpdateDTO;
import com.feedback.hafit.domain.post.dto.response.PostWithCommentsDTO;
import com.feedback.hafit.domain.post.dto.response.PostWithLikesDTO;
import com.feedback.hafit.domain.post.service.PostService;
import com.feedback.hafit.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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
    public void createPost(@RequestParam(value="files", required = false) List<MultipartFile> files,
                                              @ModelAttribute PostCreateDTO postFormDTO,
                                              Principal principal) {
        postService.createPost(postFormDTO, files, principal.getName());
    }

    @PutMapping("/{postId}") // 피드 수정
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public void updatePost(@PathVariable Long postId,
                                               @RequestParam(value = "files", required = false) List<MultipartFile> files,
                                               @ModelAttribute PostUpdateDTO postFormDTO) {
        postService.updatePost(postId, postFormDTO, files);
    }

    @DeleteMapping("/{postId}") // 피드 삭제
    public void deletePost(@PathVariable Long postId) {
        postService.deletePostById(postId);
    }

    // 1개 게시글 조회
    @GetMapping("/{postId}")
    public PostWithCommentsDTO getPostById(@PathVariable Long postId, Principal principal) {
        return postService.getPostById(postId, principal.getName());
    }

    // 전체 피드 조회
    @GetMapping
    public List<PostWithLikesDTO> getAllPosts(@RequestParam Long lastPostId,
                                              @RequestParam int size,
                                              Principal principal) {
        return postService.getAllPosts(lastPostId, size, principal.getName());
    }

    // 내가 작성한 글 조회
    @GetMapping("/my")
    public Map<String, Object> getMyPosts(Principal principal) {
        return postService.getMyPosts(principal.getName());
    }

    // 내가 좋아요 표시한 글 조회
    @GetMapping("/my/liked-posts")
    public Map<String, Object> getLikedPostsByEmail(Principal principal) {
        return postService.getLikedPostsByEmail(principal.getName());
    }
}
