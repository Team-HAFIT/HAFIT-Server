package com.feedback.hafit.domain.post.controller;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.feedback.hafit.domain.post.dto.request.PostCreateDTO;
import com.feedback.hafit.domain.post.dto.request.PostUpdateDTO;
import com.feedback.hafit.domain.post.dto.response.PostFileDTO;
import com.feedback.hafit.domain.post.dto.response.PostWithCommentsDTO;
import com.feedback.hafit.domain.post.dto.response.PostWithLikesDTO;
import com.feedback.hafit.domain.post.service.PostService;
import com.feedback.hafit.global.s3.service.S3Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.Principal;
import java.util.ArrayList;
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

    @GetMapping("/image")
    public ResponseEntity<List<InputStreamResource>> getImageFiles(@PathVariable Long postId, Principal principal) {
        String bucketName = "feedback-file-bucket";
        List<PostFileDTO> urls = postService.getPostById(postId, principal.getName()).getFiles();
        List<InputStreamResource> resources = new ArrayList<>();

        for (PostFileDTO file : urls) {
            String fileName = file.getFile_name().substring(61);
            String objectKey = "static/posts" + fileName;

            // S3 객체에 대한 사전 서명된 URL 생성
            GeneratePresignedUrlRequest urlRequest = new GeneratePresignedUrlRequest(bucketName, objectKey)
                    .withMethod(HttpMethod.GET)
                    .withExpiration(s3Service.getExpiration());

            URL url = s3Service.generatePresignedUrl(urlRequest);

            try {
                // URL에 연결하여 InputStream 가져오기
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");

                // InputStream에서 데이터를 읽어 임시 파일로 복사
                InputStream inputStream = connection.getInputStream();
                File tempFile = File.createTempFile("image", ".jpg");
                FileUtils.copyInputStreamToFile(inputStream, tempFile);

                // 임시 파일로부터 InputStreamResource 객체 생성
                InputStreamResource resource = new InputStreamResource(new FileInputStream(tempFile));
                resources.add(resource);
            } catch (IOException e) {
                // 예외 처리
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }

        // 파일을 응답으로 전달하기 위해 필요한 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        headers.setContentDisposition(ContentDisposition.attachment().filename("images.zip").build());

        return new ResponseEntity<>(resources, headers, HttpStatus.OK);
    }

}