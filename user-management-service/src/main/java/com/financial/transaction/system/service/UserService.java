package com.financial.transaction.system.service;

import com.financial.transaction.system.convertor.UserConvertor;
import com.financial.transaction.system.entity.User;
import com.financial.transaction.system.exception.EmailAlreadyExistsException;
import com.financial.transaction.system.exception.MobileNumberAlreadyExistsException;
import com.financial.transaction.system.exception.UserDoesNotExist;
import com.financial.transaction.system.repository.UserRepository;
import com.financial.transaction.system.requestDTO.UserRequestDTO;
import com.financial.transaction.system.responseDTO.UserResponseDTO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public UserResponseDTO addUser(UserRequestDTO userRequestDTO) throws MobileNumberAlreadyExistsException, EmailAlreadyExistsException {

        //CHECKS FOR MOBILE NUMBER AND EMAIL

        String mobNO = userRequestDTO.getMobNo();
        if(userRepository.findByMobNo(mobNO) != null){
            throw new MobileNumberAlreadyExistsException("Mobile number already exists.");
        }

        String email = userRequestDTO.getEmail();
        if(userRepository.findByEmail(email) != null){
            throw new EmailAlreadyExistsException("Email already exists.");
        }

        User user = UserConvertor.userRequestDtoToUser(userRequestDTO);
        userRepository.save(user);

        return UserConvertor.userToUserResponseDto(user);
    }

    @Transactional
    public String deleteUserByUserId(String userId) throws UserDoesNotExist {

        User user = userRepository.findByUserId(userId);

        if(user == null){
            throw new UserDoesNotExist("User does not exist.");
        }

        userRepository.deleteByUserId(userId);

        String toReturn = user.getFirstName() + " " + user.getLastName() + " is deleted from the database.";

        return toReturn;
    }

    public UserResponseDTO getByUserId(String userId) throws UserDoesNotExist {

        User user = userRepository.findByUserId(userId);

        if(user == null){
            throw new UserDoesNotExist("User does not exist.");
        }

        UserResponseDTO userResponseDTO = UserConvertor.userToUserResponseDto(user);

        return userResponseDTO;
    }
}
