package com.feedback.hafit.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class HelloWorldController {

    @GetMapping("hello")
    public List<String> hello() {

        return Arrays.asList("스프링부트 - 리액트 연동 테스트 (유진) (종훈) (다연)", "23.04.12");
    }
}
