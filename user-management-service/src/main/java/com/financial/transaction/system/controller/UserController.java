package com.financial.transaction.system.controller;

import com.financial.transaction.system.exception.EmailAlreadyExistsException;
import com.financial.transaction.system.exception.MobileNumberAlreadyExistsException;
import com.financial.transaction.system.exception.UserDoesNotExist;
import com.financial.transaction.system.requestDTO.UserRequestDTO;
import com.financial.transaction.system.responseDTO.UserResponseDTO;
import com.financial.transaction.system.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/add")
    public UserResponseDTO addUser(@Valid @RequestBody UserRequestDTO userRequestDTO) {
        UserResponseDTO response = new UserResponseDTO();
        try {
            response = userService.addUser(userRequestDTO);
        } catch (Exception e) {
            System.out.println("Exception while adding user");
        }
        return response;
    }

    @DeleteMapping("/deleteByUserId")
    public String deleteUser(@RequestParam String userId)  {

        String message = "Cannot delete this user.";
        try {
            return userService.deleteUserByUserId(userId);
        } catch (UserDoesNotExist e) {
            System.out.println("Exception while deleting user with Id : " + userId);
            return message;
        }
    }

    @GetMapping("/getByUserId")
    public UserResponseDTO getByUserId(@RequestParam String userId) {

        UserResponseDTO response = new UserResponseDTO();
        try {
            return userService.getByUserId(userId);
        } catch (UserDoesNotExist e) {
            System.out.println("Exception while getting user of userId : " + userId);
        }
        return response;
    }

}
