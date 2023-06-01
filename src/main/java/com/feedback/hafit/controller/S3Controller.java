package com.feedback.hafit.controller;

import com.feedback.hafit.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/upload")
@RequiredArgsConstructor
public class S3Controller {
    private final S3Service s3Service;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

}