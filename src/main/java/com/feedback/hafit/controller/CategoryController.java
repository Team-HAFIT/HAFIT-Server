package com.feedback.hafit.controller;

import com.feedback.hafit.entity.Category;
import com.feedback.hafit.entity.CategoryFormDTO;
import com.feedback.hafit.repository.CategoryRepository;
import com.feedback.hafit.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
@CrossOrigin(origins = "#")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryService categoryService;

    @PostMapping("create") // 카테고리 추가
    @CrossOrigin(origins = "#")
    public boolean create(@RequestBody CategoryFormDTO categoryFormDTO) {
        boolean isCategoryCreated = categoryService.create(categoryFormDTO);
        if(!isCategoryCreated) {
            System.out.println("카테고리 추가 실패");
            return false;
        }
        System.out.println("카테고리 추가 성공");
        return true;
    }

    @PostMapping("update") // 카테고리 수정
    @CrossOrigin(origins = "#")
    public boolean update(@RequestBody CategoryFormDTO categoryFormDTO) {
        boolean isCategoryUpdated = categoryService.update(categoryFormDTO);
        if(!isCategoryUpdated) {
            System.out.println("수정 실패");
            return false;
        }
        System.out.println("수정 성공");
        return true;
    }

    @DeleteMapping("/delete") // 카테고리 삭제
    @CrossOrigin(origins = "#")
    public boolean delete(@RequestBody CategoryFormDTO categoryFormDTO) {
        boolean isCategoryDeleted = categoryService.delete(categoryFormDTO);
        if(!isCategoryDeleted) {
            System.out.println("삭제 실패");
            return false;
        }
        System.out.println("삭제 성공");
        return true;
    }

    @GetMapping("list")
    @CrossOrigin(origins = "#")
    public ResponseEntity<Object> geyAllCategorys() {
        List<Category> categorys = categoryService.getAllCategorys();
        if(!categorys.isEmpty()) {
            return ResponseEntity.ok(categorys);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
