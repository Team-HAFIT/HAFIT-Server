package com.feedback.hafit.domain.category.service;

import com.feedback.hafit.domain.category.dto.CategoryDTO;
import com.feedback.hafit.domain.category.entity.Category;
import com.feedback.hafit.domain.category.repository.CategoryRepository;
import com.feedback.hafit.domain.user.entity.User;
import com.feedback.hafit.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    private final UserRepository userRepository;

    public CategoryDTO createCategory(CategoryDTO categoryDTO, String email) {
        try {
            User user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new EntityNotFoundException("User not found with email: " + email));

            Category category = new Category();
            category.setCategoryName(categoryDTO.getCategoryName());
            category.setUser(user);

            Category savedCategory = categoryRepository.save(category);

            return new CategoryDTO(savedCategory);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("카테고리 생성에 실패하였습니다.");
        }
    }

    public Category getById(Long id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isEmpty()) {
            throw new IllegalArgumentException("해당하는 카테고리가 없습니다.");
        }
        return optionalCategory.get();
    }

    public CategoryDTO update(CategoryDTO categoryFormDTO) {
        try {
            Optional<Category> optionalCategory = categoryRepository.findById(categoryFormDTO.getCategoryId());
            if (optionalCategory.isPresent()) {
                Category category = optionalCategory.get();
                category.setCategoryName(categoryFormDTO.getCategoryName());
                categoryRepository.save(category);
                return new CategoryDTO(category);
            } else {
                System.out.println("해당하는 카테고리를 찾을 수 없습니다.");
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean delete(Long categoryId) {
        try {
            Optional<Category> categoryOptional = categoryRepository.findById(categoryId);
            if (categoryOptional.isEmpty()) {
                // 삭제할 카테고리가 존재하지 않을 경우 처리
                return false;
            }

            Category category = categoryOptional.get();
            categoryRepository.delete(category);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    public List<Category> getAllCategories() {
        try {
            return categoryRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
