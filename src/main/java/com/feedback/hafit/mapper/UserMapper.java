package com.feedback.hafit.mapper;

import com.feedback.hafit.entity.UserDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    int userJoin(UserDTO userDTO);
    // public int emailCheck(String email);
    // public UserDTO userLogin(UserDTO userDTO);
}
