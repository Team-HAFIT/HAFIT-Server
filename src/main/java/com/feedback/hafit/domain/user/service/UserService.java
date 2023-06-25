package com.feedback.hafit.domain.user.service;

import com.feedback.hafit.domain.exerciseSet.dto.response.ExerciseSetResponseDTO;
import com.feedback.hafit.domain.exerciseSet.service.ExerciseSetService;
import com.feedback.hafit.domain.plan.entity.Plan;
import com.feedback.hafit.domain.plan.repository.PlanRepository;
import com.feedback.hafit.domain.user.dto.request.UserChangePasswordDTO;
import com.feedback.hafit.domain.user.dto.request.UserDTO;
import com.feedback.hafit.domain.user.dto.request.UserFormDTO;
import com.feedback.hafit.domain.user.dto.response.UserResponseDTO;
import com.feedback.hafit.domain.user.entity.User;
import com.feedback.hafit.domain.user.repository.UserRepository;
import com.feedback.hafit.global.s3.service.S3Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final PlanRepository planRepository;
    private final ExerciseSetService exerciseSetService;

    private final S3Service s3Service;

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

    public int phoneCheck(String phone) {
        boolean exists = userRepository.existsByPhone(phone);
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

    public UserResponseDTO getUserInfo(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id " + email));
        return new UserResponseDTO(user);
    }
    public String uploadProfileImage(MultipartFile profileImage, String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Could not find user with email: " + email));

        String s3Path = s3Service.upload(profileImage, "profiles");

        user.setImageUrl(s3Path);

        userRepository.save(user);
        // 프로필 이미지 업로드
        return s3Path;
    }

    @Transactional
    public boolean updateProfileImage(MultipartFile profileImage, String email) {
        if (profileImage == null) {
            throw new IllegalArgumentException("profileImage is null");
        }

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 사용자를 찾을 수 없습니다."));

        // 이전 프로필 이미지 삭제
        String previousProfileImage = user.getImageUrl();

        if (previousProfileImage != null) {
            s3Service.delete(previousProfileImage);
        }

        // 프로필 이미지 업로드
        String s3Path = s3Service.upload(profileImage, "profiles");
        user.setImageUrl(s3Path);
        userRepository.save(user);

        return true;
    }

    @Transactional
    public boolean deleteProfileImage(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 사용자를 찾을 수 없습니다."));

        String previousProfileImage = user.getImageUrl();

        if (previousProfileImage != null) {
            s3Service.delete(previousProfileImage);
            user.setImageUrl(null); // 이미지 URL을 null로 설정하여 프로필 이미지 제거
            userRepository.save(user);
        }
        return true;
    }


    public String getProfileImageByUserId(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 사용자를 찾을 수 없습니다."));

        return user.getImageUrl();
    }

    public List<ExerciseSetResponseDTO> findAllSets(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 사용자를 찾을 수 없습니다."));

        List<Plan> plans = planRepository.findByUser(user);

        List<ExerciseSetResponseDTO> sets = plans.stream()
                .flatMap(plan -> exerciseSetService.getByPlanId(plan.getPlanId()).stream())
                .collect(Collectors.toList());

        return sets;
    }

}
