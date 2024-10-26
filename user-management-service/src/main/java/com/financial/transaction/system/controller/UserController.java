package com.financial.transaction.system.controller;

import com.financial.transaction.system.exception.UserDoesNotExist;
import com.financial.transaction.system.requestDTO.UserRequestDTO;
import com.financial.transaction.system.response.UserResponseDto;
import com.financial.transaction.system.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @PostMapping("/add")
    public UserResponseDto addUser(@Valid @RequestBody UserRequestDTO userRequestDTO) {
        UserResponseDto response = new UserResponseDto();
        try {
            response = userService.addUser(userRequestDTO);
        } catch (Exception e) {
            LOG.info("Exception while adding user: {}", e.getLocalizedMessage());
        }
        return response;
    }

    @DeleteMapping("/deleteByUserId")
    public String deleteUser(@RequestParam String userId)  {

        String message = "Cannot delete this user.";
        try {
            return userService.deleteUserByUserId(userId);
        } catch (UserDoesNotExist e) {
            LOG.info("Exception while deleting user with Id : {}", userId);
            return message;
        }
    }

    @GetMapping("/getByUserId")
    public UserResponseDto getByUserId(@RequestParam String userId) {

        UserResponseDto response = new UserResponseDto();
        try {
            return userService.getByUserId(userId);
        } catch (UserDoesNotExist e) {
            LOG.info("Exception while getting user of userId : {}", userId);
        }
        return response;
    }

}
