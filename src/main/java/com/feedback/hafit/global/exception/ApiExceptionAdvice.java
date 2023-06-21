package com.feedback.hafit.global.exception;

import com.amazonaws.services.s3.model.AmazonS3Exception;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;

@RestControllerAdvice
@Slf4j
public class ApiExceptionAdvice {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleEntityNotFoundException(
            EntityNotFoundException e
    ) {
        log.error("EntityNotFoundException 발생!");
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> IllegalArgumentException(
            IllegalArgumentException e
    ) {
        log.error("illegal args exception 발생!");
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(AmazonS3Exception.class)
    public ResponseEntity<String> AmazonS3Exception(
            AmazonS3Exception e
    ) {
        log.error("AmazonS3Exception 발생!");
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

}
