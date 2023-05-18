package com.feedback.hafit.entity;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDTO {

    @NotNull
    private Long category_id;
    private String category_name;
    private Long user_id;

    @Builder
    public CategoryDTO(String category_name, Long user_id) {
        this.category_name = category_name;
        this.user_id = user_id;
    }

//    @Autowired
//    @JsonIgnore
//    UserRepository userRepository;
//
//    @NotNull
//    private Long category_id;
//    private String category_name;
//
//    @JsonIgnore
//    private Long user_id;
//
//    public Category toEntity() {
//        User user = userRepository.findById(user_id).orElse(null);
//        Category category = Category.builder()
//                .category_name(category_name)
//                .user(user)
//                .build();
//        return category;
//    }

//    @Builder
//    public CategoryFormDTO(String category_name, Long user_id) {
//        this.category_name = category_name;
//        this.user_id = user_id;
//    }

}