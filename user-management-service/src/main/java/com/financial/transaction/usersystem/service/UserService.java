package com.financial.transaction.usersystem.service;

import com.financial.transaction.usersystem.convertor.UserConvertor;
import com.financial.transaction.usersystem.entity.User;
import com.financial.transaction.usersystem.exception.EmailAlreadyExistsException;
import com.financial.transaction.usersystem.exception.MobileNumberAlreadyExistsException;
import com.financial.transaction.usersystem.exception.UserDoesNotExist;
import com.financial.transaction.usersystem.kafka.publisher.UserChangeEventPublisher;
import com.financial.transaction.usersystem.repository.UserRepository;
import com.financial.transaction.usersystem.requestDTO.UserRequestDTO;
import com.financial.transaction.system.requests.AccountRequestDto;
import com.financial.transaction.system.response.AccountResponseDto;
import com.financial.transaction.system.response.UserResponseDto;
import com.financial.transaction.system.service.AccountServiceClient;
import com.financial.transaction.system.service.GenericApiService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserChangeEventPublisher userChangeEventPublisher;

    @Autowired
    GenericApiService genericApiService;

    @Autowired
    AccountServiceClient accountServiceClient;

    public UserResponseDto addUser(UserRequestDTO userRequestDTO) throws MobileNumberAlreadyExistsException, EmailAlreadyExistsException {

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

        UserResponseDto responseDTO = UserConvertor.userToUserResponseDto(user);

        userChangeEventPublisher.publishUserChangeEventToKafka(responseDTO);

        return responseDTO;
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

    public UserResponseDto getByUserId(String userId) throws UserDoesNotExist {

        User user = userRepository.findByUserId(userId);

        if(user == null){
            throw new UserDoesNotExist("User does not exist.");
        }

        UserResponseDto userResponseDTO = UserConvertor.userToUserResponseDto(user);

        return userResponseDTO;
    }

    public AccountResponseDto addAccount(AccountRequestDto request) throws Exception {
        AccountResponseDto responseDto = new AccountResponseDto();

        if (request.getUserId() == null) {
            LOG.info("null userId given creating account request: {}", request);
            throw new RuntimeException("Invalid UserId");
        }
        User user = userRepository.findByUserId(request.getUserId());
        if (user == null) {
            LOG.info("User does not exists for given userId: {}", request.getUserId());
            throw new Exception("Invalid userId");
        }

        return accountServiceClient.createAccount(request);
    }
}
