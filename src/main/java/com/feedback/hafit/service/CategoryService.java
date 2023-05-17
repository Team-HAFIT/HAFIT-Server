package com.feedback.hafit.service;

import com.feedback.hafit.entity.Category;
import com.feedback.hafit.entity.CategoryFormDTO;
import com.feedback.hafit.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    public boolean created(CategoryFormDTO categoryFormDTO) {
        try {
            categoryRepository.save(categoryFormDTO.toEntity());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updated(CategoryFormDTO categoryFormDTO) {
        try {
            Optional<Category> optionalCategory = categoryRepository.findById(categoryFormDTO.getCategory_id());
            if(optionalCategory.isPresent()) {
                Category category = optionalCategory.get();
                category.setCategory_name(categoryFormDTO.getCategory_name());
                categoryRepository.save(category);
                return true;
            } else {
                System.out.println("해당하는 카테고리를 찾을 수 없습니다.");
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
