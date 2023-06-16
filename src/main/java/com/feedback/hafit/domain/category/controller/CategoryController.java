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
        boolean createdCategory = categoryService.createCategory(categoryRequestDTO, principal.getName());
        if (createdCategory) {
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{categoryId}") // 카테고리 수정
    public ResponseEntity<Boolean> updateCategory(@PathVariable Long categoryId, @RequestBody CategoryRequestDTO categoryRequestDTO) {
        categoryRequestDTO.setCategoryId(categoryId);
        boolean isUpdated = categoryService.updateCategory(categoryRequestDTO);
        if (isUpdated) {
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{categoryId}") // 카테고리 삭제
    public ResponseEntity<Boolean> deleteCategory(@PathVariable Long categoryId) {
        boolean isDeleted = categoryService.deleteCategory(categoryId);
        if (isDeleted) {
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping // 카테고리 목록 조회
    public ResponseEntity<List<CategoryResponseDTO>> getAllCategories() {
        List<CategoryResponseDTO> categoryResponseDTOList = categoryService.getAllCategories()
                .stream()
                .map(CategoryResponseDTO::new)
                .collect(Collectors.toList());
        if (!categoryResponseDTOList.isEmpty()) {
            return ResponseEntity.ok(categoryResponseDTOList);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    // 카테고리별 게시글 조회
    @GetMapping("/{categoryId}/posts")
    public ResponseEntity<List<PostDTO>> getPostsByCategory(@PathVariable Long categoryId) {
        log.info(String.valueOf(categoryId));
        List<PostDTO> posts = categoryService.getPostsByCategory(categoryId);
        if (!posts.isEmpty()) {
            return ResponseEntity.ok(posts);
        } else {
            return ResponseEntity.noContent().build();
        }
    }
}
