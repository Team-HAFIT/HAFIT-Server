package com.feedback.hafit.domain.user.service;

import com.amazonaws.services.kms.model.NotFoundException;
import com.feedback.hafit.domain.comment.repository.CommentRepository;
import com.feedback.hafit.domain.post.dto.response.PostFileDTO;
import com.feedback.hafit.domain.post.dto.response.PostForUserDTO;
import com.feedback.hafit.domain.post.entity.Post;
import com.feedback.hafit.domain.post.repository.PostRepository;
import com.feedback.hafit.domain.post.service.PostService;
import com.feedback.hafit.domain.postLike.entity.PostLike;
import com.feedback.hafit.domain.postLike.repository.PostLikeRepository;
import com.feedback.hafit.domain.user.dto.UserChangePasswordDTO;
import com.feedback.hafit.domain.user.dto.UserDTO;
import com.feedback.hafit.domain.user.dto.UserFormDTO;
import com.feedback.hafit.domain.user.entity.User;
import com.feedback.hafit.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PostLikeRepository postLikeRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final PasswordEncoder passwordEncoder;
    private final PostService postService;


    public User getById(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isEmpty())throw new IllegalArgumentException("존재하지 않는 유저입니다.");
        return userOptional.get();
    }

    public User getByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found with email: " + email));
    }

    public void signup(UserFormDTO userFormDTO) {
        User user = userFormDTO.toEntity();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
    }

    public boolean deleteUser(String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) { // Optional에서 User를 가져올 수 있는지 확인
            User user = userOptional.get();
            userRepository.delete(user);
            return true; // 삭제 성공을 나타내는 true 반환
        } else {
            return false; // 삭제 실패를 나타내는 false 반환
        }
    }

    public int emailCheck(String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        return userOptional.isPresent() ? 1 : 0;
    }

    public boolean updateUser(String email, UserDTO userDTO) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setCarrier(user.getCarrier());
            user.setPhone(userDTO.getPhone());
            user.setHeight(userDTO.getHeight());
            user.setWeight(userDTO.getWeight());
            user.setSex(userDTO.getSex());
            user.setBirthday(userDTO.getBirthday());
            user.setImageUrl(userDTO.getImageUrl());
            userRepository.save(user);
            return true;
        } else {
            return false;
        }
    }

    public boolean changePassword(String email, UserChangePasswordDTO changePasswordDTO) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (passwordEncoder.matches(changePasswordDTO.getOldPassword(), user.getPassword())) {
                String encodedNewPassword = passwordEncoder.encode(changePasswordDTO.getNewPassword());
                // 비밀번호 유효성 검사 이후에 암호화 및 저장을 수행합니다.
                user.setPassword(encodedNewPassword);
                userRepository.save(user);
                return true;
            }
        }
        return false;
    }

    public UserDTO getUserInfoByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id " + email));
        return mapToUserDTO(user);
    }

    private UserDTO mapToUserDTO(User user) {
        return UserDTO.builder()
                .userId(user.getUserId())
                .email(user.getEmail())
                .name(user.getName())
                .carrier(user.getCarrier())
                .phone(user.getPhone())
                .sex(user.getSex())
                .imageUrl(user.getImageUrl())
                .weight(user.getWeight())
                .height(user.getHeight())
                .birthday(user.getBirthday())
                .role(user.getRole())
                .build();
    }

    // 내가 좋아요한 게시글
    public Map<String, Object> getLikedPostsByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id " + email));
        List<PostLike> postLikes = postLikeRepository.findByUser(user);
        List<PostForUserDTO> likedPosts = new ArrayList<>();

        for (PostLike postLike : postLikes) {
            Post post = postLike.getPost();
            List<PostFileDTO> postFileDTOS = postService.getFileImageDTOsForPost(post);
            PostForUserDTO postDTO = new PostForUserDTO(post, postFileDTOS);
            likedPosts.add(postDTO);
        }

        Map<String, Object> result = new HashMap<>();
        result.put("count", likedPosts.size());
        result.put("posts", likedPosts);

        return result;
    }

    // 내가 작성한 게시글
    public List<PostForUserDTO> getUserPosts(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Could not find user with name: " + email));

        List<Post> posts = postRepository.findAll();
        List<PostForUserDTO> postedPosts = new ArrayList<>();

        for (Post post : posts) {
            List<PostFileDTO> postFileDTOS = postService.getFileImageDTOsForPost(post);
            Long totalLikes = postLikeRepository.countLikesByPost(post);

            PostForUserDTO postWithLikesDTO = new PostForUserDTO(post, postFileDTOS);
            postedPosts.add(postWithLikesDTO);
        }

        return postedPosts;
    }
}
