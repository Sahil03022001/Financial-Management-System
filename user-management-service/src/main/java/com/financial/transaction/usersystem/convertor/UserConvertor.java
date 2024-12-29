package com.financial.transaction.usersystem.convertor;

import com.financial.transaction.usersystem.entity.User;
import com.financial.transaction.system.requests.UserRequestDTO;
import com.financial.transaction.system.response.UserResponseDto;
import com.financial.transaction.usersystem.utils.UtilsManager;
import lombok.experimental.UtilityClass;

import java.util.Date;

@UtilityClass
public class UserConvertor {

    public static User userRequestDtoToUser(UserRequestDTO userRequestDTO){


        return User.builder()
                .firstName(userRequestDTO.getFirstName())
                .lastName(userRequestDTO.getLastName())
                .userId(UtilsManager.generateRandomId())
                .dateOfBirth(userRequestDTO.getDateOfBirth())
                .email(userRequestDTO.getEmail())
                .mobNo(userRequestDTO.getMobNo())
                .password(userRequestDTO.getPassword())
                .created(new Date())
                .updated(new Date())
                .build();
    }

    public static UserResponseDto userToUserResponseDto(User user){

        return UserResponseDto.builder()
                .userId(user.getUserId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .mobNo(user.getMobNo())
                .email(user.getEmail())
                .dateOfBirth(user.getDateOfBirth())
                .build();
    }
}
