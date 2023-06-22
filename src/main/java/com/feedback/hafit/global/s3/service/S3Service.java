package com.feedback.hafit.global.s3.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class S3Service {

    private final AmazonS3 amazonS3;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Value("${cloud.aws.s3.bucket.directory-name:static}")
    private String S3_BUCKET_DIRECTORY_NAME = "static";

    private Logger log = LoggerFactory.getLogger(S3Service.class);

    /**
     * S3 파일 업로드
     *
     * @param multipartFile 실제 파일
     * @param dirName       폴더 명
     * @return s3 파일 url
     */

    public String upload(MultipartFile multipartFile, String dirName) {
        // 메타데이터 설정
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(multipartFile.getContentType());
        objectMetadata.setContentLength(multipartFile.getSize());

        // 실제 S3 bucket 디렉토리명 설정
        // 파일명 중복을 방지하기 위한 UUID 추가

        // 경로가 "//"인 경우 replace로 "/"로 변경
        String path = S3_BUCKET_DIRECTORY_NAME + "/" + dirName + "/" + UUID.randomUUID();
        path = path.replace("//", "/");
        String fileName = path + "." + UUID.randomUUID();//multipartFile.getOriginalFilename();

        //        removeNewFile(multipartFile);
        return putS3(multipartFile, objectMetadata, fileName);
    }

    // 1. 로컬에 파일생성
    private File convert(MultipartFile file) throws IOException {
        // originalFilename 변수에 file.getOriginalFilename()을 넣고 file.getOriginalFilename()이 빈 문자열이면 temporary로 지정
        String originalFilename = file.getOriginalFilename();
        if (originalFilename == null || originalFilename.isBlank()) {
            originalFilename = "temporary";
        }

        File convertFile = new File(originalFilename);

        if (convertFile.createNewFile()) {
            return convertFile;
        }

        throw new IOException("파일을 서버에 저장하는데 실패했습니다.");
    }

    private String putS3(MultipartFile uploadFile, ObjectMetadata objectMetadata, String fileName) {
        String res = "";
        try (InputStream inputStream = uploadFile.getInputStream()) {
            amazonS3.putObject(new PutObjectRequest(bucket, fileName, inputStream, objectMetadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead));
            res = amazonS3.getUrl(bucket, fileName).toString();
        } catch (IOException e) {
            log.error("S3 파일 업로드에 실패했습니다. {}", e.getMessage());
            throw new IllegalStateException("S3 파일 업로드에 실패했습니다.");
        }
        return res;
    }

    // 3. 로컬에 생성된 파일삭제
    private void removeNewFile(File targetFile) {
        if (targetFile.delete()) {
            log.info("File delete success");
            return;
        }
        log.info("PostFile delete fail");
    }

    public void delete(String fileName) {
        log.info("File Delete: " + fileName); // url
        log.info("File : " + fileName.substring(61)); // https://feedback-file-bucket.s3.ap-northeast-2.amazonaws.com/ 잘라냄
        amazonS3.deleteObject(bucket, fileName.substring(61));
        System.out.println(fileName);
    }

    public byte[] downloadImage(String objectKey) {
        try {
            S3Object s3Object = amazonS3.getObject(bucket, objectKey);
            S3ObjectInputStream objectInputStream = s3Object.getObjectContent();

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = objectInputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            return outputStream.toByteArray();
        } catch (IOException e) {
            log.error("Error occurred during image download: {}", e.getMessage());
            throw new RuntimeException("Error occurred during image download", e);
        }
    }

    public URL generatePresignedUrl(GeneratePresignedUrlRequest urlRequest) {
        return amazonS3.generatePresignedUrl(urlRequest);
    }

    // 제한 시간을 두는 코드, 이미지를 파일로 받아오기 위한 코드라 필요 x, 혹시 몰라 남겨둠
    public Date getExpiration() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.HOUR, 1); // 1시간 후로 설정

        return calendar.getTime();
    }
}
