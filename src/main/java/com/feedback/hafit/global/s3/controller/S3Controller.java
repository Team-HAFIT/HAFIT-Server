package com.feedback.hafit.global.s3.controller;

import com.feedback.hafit.global.s3.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
@RequiredArgsConstructor
public class S3Controller {
    private final S3Service s3Service;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @PostMapping("/upload")
    public String upload(@RequestPart(value="file",required = false) MultipartFile file) {
        // 업로드된 파일을 S3에 저장하고 파일 URL을 반환
        String fileUrl = s3Service.upload(file, bucket);
        return fileUrl;
    }
}
