package com.feedback.hafit.domain.category.controller;

import com.feedback.hafit.domain.category.dto.request.CategoryRequestDTO;
import com.feedback.hafit.domain.category.dto.response.CategoryResponseDTO;
import com.feedback.hafit.domain.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
@Slf4j
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping // 카테고리 추가
    public void createCategory(@RequestBody CategoryRequestDTO categoryRequestDTO, Principal principal) {
        categoryService.createCategory(categoryRequestDTO, principal.getName());
    }

    @PutMapping("/{categoryId}") // 카테고리 수정
    public void updateCategory(@PathVariable Long categoryId, @RequestBody CategoryRequestDTO categoryRequestDTO) {
        categoryService.updateCategory(categoryId, categoryRequestDTO);
    }

    @DeleteMapping("/{categoryId}") // 카테고리 삭제
    public void deleteCategory(@PathVariable Long categoryId) {
        categoryService.deleteCategory(categoryId);
    }

    @GetMapping // 카테고리 목록 조회
    public List<CategoryResponseDTO> getAllCategories() {
        return categoryService.getAllCategories();
    }
}
