package com.feedback.hafit.domain.post.controller;

import com.feedback.hafit.domain.post.dto.request.PostCreateDTO;
import com.feedback.hafit.domain.post.dto.request.PostUpdateDTO;
import com.feedback.hafit.domain.post.dto.response.PostWithCommentsDTO;
import com.feedback.hafit.domain.post.dto.response.PostWithLikesDTO;
import com.feedback.hafit.domain.post.service.PostService;
import com.feedback.hafit.global.s3.service.S3Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.Principal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
@Slf4j
public class PostController {

    private final PostService postService;
    private final S3Service s3Service;

    @PostMapping
    public void createPost(@RequestParam(value = "files", required = false) List<MultipartFile> files,
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

    @GetMapping("/img/{fileId}")
    public ResponseEntity<InputStreamResource> getPostImageById(@PathVariable Long fileId, Principal principal) {
        String fileName = postService.getFileName(fileId);
        try {
            // URL에 연결하여 미디어 다운로드
            URL url = new URL(fileName);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // 미디어 타입 확인
            String mediaType = connection.getContentType();

            // 미디어 파일을 InputStream으로 가져오기
            InputStream inputStream = connection.getInputStream();

            // InputStream을 리소스로 변환
            InputStreamResource resource = new InputStreamResource(inputStream);

            // 응답에 Content-Type을 설정하기 위한 MediaType 객체 생성
            MediaType contentType = MediaType.parseMediaType(mediaType);

            // 응답 헤더에 Content-Type 설정
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(contentType);

            // 이미지인 경우
            if (contentType.isCompatibleWith(MediaType.IMAGE_JPEG) || contentType.isCompatibleWith(MediaType.IMAGE_PNG)) {
                // 이미지 파일의 경우 Content-Disposition을 inline으로 설정하여 브라우저에 표시
                headers.setContentDisposition(ContentDisposition.inline().build());
            }
            // 동영상인 경우
            else if (contentType.isCompatibleWith(MediaType.valueOf("video/mp4"))) {
                // 동영상 파일의 경우 Content-Disposition을 attachment로 설정하여 다운로드
                headers.setContentDisposition(ContentDisposition.attachment().build());
            }

            return new ResponseEntity<>(resource, headers, HttpStatus.OK);
        } catch (IOException e) {
            // 예외 처리
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // 전체 피드 조회
    @GetMapping
    public List<PostWithLikesDTO> getAllPosts(@RequestParam Long lastPostId,
                                              @RequestParam int size,
                                              Principal principal) {
        return postService.getAllPosts(lastPostId, size, principal.getName());
    }

    // 카테고리별 게시글 조회
    @GetMapping("/category/{categoryId}")
    public List<PostWithLikesDTO> getPostsByCategory(@RequestParam Long lastPostId,
                                                     @RequestParam int size,
                                                     @PathVariable Long categoryId, Principal principal) {
        return postService.getPostsByCategory(lastPostId, size, categoryId, principal.getName());
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