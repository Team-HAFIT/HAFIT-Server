package com.feedback.hafit.controller;

import com.feedback.hafit.entity.Category;
import com.feedback.hafit.entity.CategoryDTO;
import com.feedback.hafit.repository.CategoryRepository;
import com.feedback.hafit.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/create") // 카테고리 추가
    public CategoryDTO create(@RequestBody CategoryDTO categoryFormDTO, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        boolean isCategoryCreated = categoryService.createCategory(categoryFormDTO, userId);
        if (!isCategoryCreated) {
            System.out.println("카테고리 추가 실패");
            return null; // 또는 실패 시에 적절한 응답을 반환할 수 있는 방법으로 변경
        }
        System.out.println("카테고리 추가 성공");
        return categoryFormDTO;
    }

    @PostMapping("/update") // 카테고리 수정
    public CategoryDTO update(@RequestBody CategoryDTO categoryFormDTO) {
        boolean isCategoryUpdated = categoryService.update(categoryFormDTO);
        if (!isCategoryUpdated) {
            System.out.println("수정 실패");
            return null; // 또는 실패 시에 적절한 응답을 반환할 수 있는 방법으로 변경
        }
        System.out.println("수정 성공");
        return categoryFormDTO;
    }

    @DeleteMapping("/delete") // 카테고리 삭제
    public boolean delete(@RequestBody CategoryDTO categoryFormDTO) {
        boolean isCategoryDeleted = categoryService.delete(categoryFormDTO);
        if (!isCategoryDeleted) {
            System.out.println("삭제 실패");
            return false;
        }
        System.out.println("삭제 성공");
        return true;
    }

    @GetMapping("/list") // 카테고리 목록 조회
    public ResponseEntity<List<CategoryDTO>> getAllCategory() {
        List<Category> categoryList = categoryService.getAllCategorys();
        List<CategoryDTO> categoryDTOList = new ArrayList<>();

        for (Category category : categoryList) {
            CategoryDTO categoryDTO = new CategoryDTO();
            categoryDTO.setCategory_id(category.getCategory_id());
            categoryDTO.setCategory_name(category.getCategory_name());

            categoryDTOList.add(categoryDTO);
        }

        if (!categoryDTOList.isEmpty()) {
            return ResponseEntity.ok(categoryDTOList);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
