package com.feedback.hafit.domain.category.controller;

import com.feedback.hafit.domain.category.dto.request.CategoryRequestDTO;
import com.feedback.hafit.domain.category.dto.response.CategoryResponseDTO;
import com.feedback.hafit.domain.category.service.CategoryService;
import com.feedback.hafit.domain.post.dto.response.PostDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
@Slf4j
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping // 카테고리 추가
    public ResponseEntity<Boolean> createCategory(@RequestBody CategoryRequestDTO categoryRequestDTO, Principal principal) {
        categoryService.createCategory(categoryRequestDTO, principal.getName());
        return ResponseEntity.ok(true);
    }

    @PutMapping("/{categoryId}") // 카테고리 수정
    public ResponseEntity<Boolean> updateCategory(@PathVariable Long categoryId, @RequestBody CategoryRequestDTO categoryRequestDTO) {
        categoryRequestDTO.setCategoryId(categoryId);
        categoryService.updateCategory(categoryRequestDTO);
        return ResponseEntity.ok(true);
    }

    @DeleteMapping("/{categoryId}") // 카테고리 삭제
    public ResponseEntity<Boolean> deleteCategory(@PathVariable Long categoryId) {
        categoryService.deleteCategory(categoryId);
        return ResponseEntity.ok(true);
    }

    @GetMapping // 카테고리 목록 조회
    public ResponseEntity<List<CategoryResponseDTO>> getAllCategories() {
        List<CategoryResponseDTO> categoryResponseDTOList = categoryService.getAllCategories()
                .stream()
                .map(CategoryResponseDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(categoryResponseDTOList);
    }

    // 카테고리별 게시글 조회
    @GetMapping("/{categoryId}/posts")
    public ResponseEntity<List<PostDTO>> getPostsByCategory(@PathVariable Long categoryId) {
        log.info(String.valueOf(categoryId));
        List<PostDTO> posts = categoryService.getPostsByCategory(categoryId);
        return ResponseEntity.ok(posts);
    }
}
