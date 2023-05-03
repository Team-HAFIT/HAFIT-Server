package com.feedback.hafit.controller;

import com.feedback.hafit.entity.Category;
import com.feedback.hafit.entity.CategoryFormDTO;
import com.feedback.hafit.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/insertCategory")
    public String insertCategory(@Valid CategoryFormDTO categoryFormDTO, Model model) {
        try {
            Category category = Category.createCategory(categoryFormDTO);
            categoryService.categoryInsert(category);
        } catch (IllegalStateException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "/category/categoryInsert";
        }
        return "redirect:/";
    }
}
