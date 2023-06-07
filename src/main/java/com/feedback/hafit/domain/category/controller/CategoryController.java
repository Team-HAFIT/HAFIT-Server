package com.feedback.hafit.domain.category.controller;

import com.feedback.hafit.domain.category.dto.CategoryDTO;
import com.feedback.hafit.domain.category.service.CategoryService;
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
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO categoryDTO, Principal principal) {

        boolean isCategoryCreated = categoryService.createCategory(categoryDTO, principal.getName());
        if (!isCategoryCreated) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(categoryDTO);
    }

    @PutMapping("/{categoryId}") // 카테고리 수정
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable Long categoryId, @RequestBody CategoryDTO categoryDTO) {
        categoryDTO.setCategoryId(categoryId);
        boolean isCategoryUpdated = categoryService.update(categoryDTO);
        if (!isCategoryUpdated) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(categoryDTO);
    }

    @DeleteMapping("/{categoryId}") // 카테고리 삭제
    public ResponseEntity<Void> deleteCategory(@PathVariable Long categoryId) {
        boolean isCategoryDeleted = categoryService.delete(categoryId);
        if (!isCategoryDeleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping // 카테고리 목록 조회
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        List<CategoryDTO> categoryDTOList = categoryService.getAllCategories()
                .stream()
                .map(category -> new CategoryDTO(category.getCategoryId(), category.getCategoryName(), category.getUserEmail()))
                .collect(Collectors.toList());

        if (!categoryDTOList.isEmpty()) {
            return ResponseEntity.ok(categoryDTOList);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

}
