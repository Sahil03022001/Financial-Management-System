package com.financial.transaction.system.convertor;

import com.financial.transaction.system.entity.User;
import com.financial.transaction.system.requestDTO.UserRequestDTO;
import com.financial.transaction.system.responseDTO.UserResponseDTO;
import com.financial.transaction.system.utils.UtilsManager;
import lombok.experimental.UtilityClass;

import java.util.Date;
import java.util.UUID;

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
                .address(userRequestDTO.getAddress())
                .created(new Date())
                .updated(new Date())
                .build();
    }

    public static UserResponseDTO userToUserResponseDto(User user){

        return UserResponseDTO.builder()
                .userId(user.getUserId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .mobNo(user.getMobNo())
                .email(user.getEmail())
                .address(user.getAddress())
                .dateOfBirth(user.getDateOfBirth())
                .build();
    }
}
