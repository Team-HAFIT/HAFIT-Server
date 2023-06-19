package com.feedback.hafit.domain.user.service;

import com.amazonaws.services.kms.model.NotFoundException;
import com.feedback.hafit.domain.comment.dto.response.CommentForUserDTO;
import com.feedback.hafit.domain.comment.entity.Comment;
import com.feedback.hafit.domain.comment.repository.CommentRepository;
import com.feedback.hafit.domain.post.dto.response.PostFileDTO;
import com.feedback.hafit.domain.post.dto.response.PostForUserDTO;
import com.feedback.hafit.domain.post.entity.Post;
import com.feedback.hafit.domain.post.repository.PostRepository;
import com.feedback.hafit.domain.post.service.PostService;
import com.feedback.hafit.domain.postLike.entity.PostLike;
import com.feedback.hafit.domain.postLike.repository.PostLikeRepository;
import com.feedback.hafit.domain.user.dto.request.UserChangePasswordDTO;
import com.feedback.hafit.domain.user.dto.request.UserDTO;
import com.feedback.hafit.domain.user.dto.request.UserFormDTO;
import com.feedback.hafit.domain.user.dto.response.UserResponseDTO;
import com.feedback.hafit.domain.user.entity.User;
import com.feedback.hafit.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public void signup(UserFormDTO userFormDTO) {
        User user = userFormDTO.toEntity();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
    }

    public void deleteUser(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id " + email));
        userRepository.delete(user);
    }

    public int emailCheck(String email) {
        boolean exists = userRepository.existsByEmail(email);
        return exists ? 1 : 0;
    }

    public void updateUser(String email, UserDTO userDTO) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id " + email));
        user.setCarrier(user.getCarrier());
        user.setPhone(userDTO.getPhone());
        user.setHeight(userDTO.getHeight());
        user.setWeight(userDTO.getWeight());
        user.setSex(userDTO.getSex());
        user.setBirthday(userDTO.getBirthday());
        user.setImageUrl(userDTO.getImageUrl());
        userRepository.save(user);
    }

    public void changePassword(String email, UserChangePasswordDTO changePasswordDTO) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id " + email));
        if (passwordEncoder.matches(changePasswordDTO.getOldPassword(), user.getPassword())) {
            String encodedNewPassword = passwordEncoder.encode(changePasswordDTO.getNewPassword());
            user.setPassword(encodedNewPassword);
            userRepository.save(user);
        } else {
            throw new IllegalArgumentException("Invalid old password");
        }
    }

    public UserResponseDTO getUserInfoByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id " + email));
        return new UserResponseDTO(user);
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
    public Map<String, Object> getMyPosts(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Could not find user with email: " + email));

        List<Post> myPosts = postRepository.findByUser(user);
        List<PostForUserDTO> postedPosts = new ArrayList<>();

        for (Post post : myPosts) {
            List<PostFileDTO> postFileDTOS = postService.getFileImageDTOsForPost(post);
            PostForUserDTO postDTO = new PostForUserDTO(post, postFileDTOS);
            postedPosts.add(postDTO);
        }

        Map<String, Object> result = new HashMap<>();
        result.put("count", postedPosts.size());
        result.put("posts", postedPosts);

        return result;
    }

    public Map<String, Object> getMyComments(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Could not find user with email: " + email));

        List<Comment> myComments = commentRepository.findByUser(user);
        List<CommentForUserDTO> postedComments = new ArrayList<>();

        for (Comment comment : myComments) {
            Long postId = comment.getPost().getPostId();
            Post post = postRepository.findById(postId)
                    .orElseThrow(() -> new EntityNotFoundException("Post not found with id: " + postId));
            List<PostFileDTO> postFileDTOS = postService.getFileImageDTOsForPost(post);
            CommentForUserDTO commentDTO = new CommentForUserDTO(comment, postFileDTOS);
            postedComments.add(commentDTO);
        }

        Map<String, Object> result = new HashMap<>();
        result.put("count", postedComments.size());
        result.put("comments", postedComments);

        return result;
    }
}
