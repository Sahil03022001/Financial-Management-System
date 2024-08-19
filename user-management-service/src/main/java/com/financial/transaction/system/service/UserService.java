package com.financial.transaction.system.service;

import com.financial.transaction.system.convertor.UserConvertor;
import com.financial.transaction.system.entity.User;
import com.financial.transaction.system.repository.UserRepository;
import com.financial.transaction.system.requestDTO.UserRequestDTO;
import com.financial.transaction.system.responseDTO.UserResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public UserResponseDTO addUser(UserRequestDTO userRequestDTO) {

        User user = UserConvertor.userRequestDtoToUser(userRequestDTO);
        userRepository.save(user);

        return UserConvertor.userToUserResponseDto(user);
    }
}
