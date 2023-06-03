package com.feedback.hafit.domain.category.service;

import com.feedback.hafit.domain.category.entity.Category;
import com.feedback.hafit.domain.user.service.UserService;
import com.feedback.hafit.domain.category.dto.CategoryDTO;
import com.feedback.hafit.domain.user.entity.User;
import com.feedback.hafit.domain.category.repository.CategoryRepository;
import com.feedback.hafit.domain.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    private final UserRepository userRepository;
    @Autowired
    public CategoryService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    UserService userService;

    public boolean createCategory(CategoryDTO categoryFormDTO, Long userId) {
        User user = userService.getUserById(userId);
        if (user == null) {
            return false;
        }

        try {
            Category category = Category.builder()
                    .category_name(categoryFormDTO.getCategory_name())
                    .user(user)
                    .build();

            // Category 저장 또는 처리 로직 작성
            Category savedCategory = categoryRepository.save(category);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(CategoryDTO categoryFormDTO) {
        try {
            Optional<Category> optionalCategory = categoryRepository.findById(categoryFormDTO.getCategory_id());
            if (optionalCategory.isPresent()) {
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

    public boolean delete(CategoryDTO categoryFormDTO) {
        try {
            Optional<Category> optionalCategory = categoryRepository.findById(categoryFormDTO.getCategory_id());
            if (optionalCategory.isPresent()) {
                Category category = optionalCategory.get();
                categoryRepository.delete(category);
                return true;
            } else {
                System.out.println("해당하는 카테고리를 삭제할 수 없습니다.");
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Category> getAllCategorys() {
        try {
            return categoryRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
