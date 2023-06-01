package com.feedback.hafit.service;

import com.feedback.hafit.config.S3Config;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class S3Service {

    private final S3Config s3Config;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

//	public String uploadFiles(MultipartFile multipartFile, String dirName) throws IOException {
//		File uploadFile = convert(multipartFile)
//	}
}
