package com.feedback.hafit.domain.category.service;

import com.feedback.hafit.domain.category.dto.request.CategoryRequestDTO;
import com.feedback.hafit.domain.category.entity.Category;
import com.feedback.hafit.domain.category.repository.CategoryRepository;
import com.feedback.hafit.domain.post.dto.response.PostDTO;
import com.feedback.hafit.domain.post.dto.response.PostFileDTO;
import com.feedback.hafit.domain.post.entity.Post;
import com.feedback.hafit.domain.post.entity.PostFile;
import com.feedback.hafit.domain.post.repository.PostRepository;
import com.feedback.hafit.domain.user.entity.User;
import com.feedback.hafit.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public boolean createCategory(CategoryRequestDTO categoryRequestDTO, String email) {
        try {
            User user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new EntityNotFoundException("User not found with email: " + email));

            Category category = new Category();
            category.setCategoryName(categoryRequestDTO.getCategoryName());
            category.setUser(user);

            Category savedCategory = categoryRepository.save(category);

            return savedCategory != null;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(CategoryRequestDTO categoryFormDTO) {
        try {
            Optional<Category> optionalCategory = categoryRepository.findById(categoryFormDTO.getCategoryId());
            if (optionalCategory.isPresent()) {
                Category category = optionalCategory.get();
                category.setCategoryName(categoryFormDTO.getCategoryName());
                categoryRepository.save(category);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
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

    public List<PostDTO> getPostsByCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new EntityNotFoundException("Category not found with ID: " + categoryId));

        List<Post> posts = postRepository.findByCategory(category);
        List<PostDTO> postDTOs = new ArrayList<>();

        for (Post post : posts) {
            List<PostFileDTO> postFileDTOS = getFileImageDTOsForPost(post);
            PostDTO postDTO = new PostDTO(post, postFileDTOS);
            postDTOs.add(postDTO);
        }

        return postDTOs;
    }

    private List<PostFileDTO> getFileImageDTOsForPost(Post post) {
        List<PostFile> postFiles = post.getPostFiles();
        List<PostFileDTO> postFileDTOS = new ArrayList<>();

        for (PostFile postFile : postFiles) {
            PostFileDTO postFileDTO = new PostFileDTO(postFile);
            postFileDTOS.add(postFileDTO);
        }

        return postFileDTOS;
    }

}

