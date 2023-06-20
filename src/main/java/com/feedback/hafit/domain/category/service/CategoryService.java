package com.feedback.hafit.domain.category.service;

import com.feedback.hafit.domain.category.dto.request.CategoryRequestDTO;
import com.feedback.hafit.domain.category.entity.Category;
import com.feedback.hafit.domain.category.repository.CategoryRepository;
import com.feedback.hafit.domain.user.entity.User;
import com.feedback.hafit.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    public void createCategory(CategoryRequestDTO categoryRequestDTO, String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found with email: " + email));

        Category category = new Category();
        category.setCategory_name(categoryRequestDTO.getCategory_name());
        category.setUser(user);
        categoryRepository.save(category);
    }

    public void updateCategory(Long categoryId, CategoryRequestDTO categoryFormDTO) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new EntityNotFoundException("Category not found with categoryId: " + categoryId));
        category.setCategory_name(categoryFormDTO.getCategory_name());
        categoryRepository.save(category);
    }

    public void deleteCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new EntityNotFoundException("Category not found with categoryId: " + categoryId));
        categoryRepository.delete(category);
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

}

