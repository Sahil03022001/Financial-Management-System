package com.financial.transaction.usersystem.controller;

import com.financial.transaction.system.requests.RegisterRequest;
import com.financial.transaction.system.requests.UserRequestDTO;
import com.financial.transaction.system.response.UserResponseDto;
import com.financial.transaction.usersystem.service.UserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private static final Logger LOG = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> register(@RequestBody @Valid RegisterRequest request) {
        UserResponseDto response = new UserResponseDto();
        try {
            //encode password first before sending create user request

            UserRequestDTO userRequestDTO = new UserRequestDTO();
            userRequestDTO.setFirstName(request.getFirstName());
            userRequestDTO.setLastName(request.getLastName());
            userRequestDTO.setEmail(request.getEmail());
            userRequestDTO.setMobNo(request.getMobNo());
            userRequestDTO.setDateOfBirth(request.getDateOfBirth());
            userRequestDTO.setPassword(request.getPassword());
            response = userService.addUser(userRequestDTO);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            LOG.error("Exception while creating User: {}", e.getMessage());
            response.setSuccessful(false);
            response.getErrors().add("Error in registering user");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
