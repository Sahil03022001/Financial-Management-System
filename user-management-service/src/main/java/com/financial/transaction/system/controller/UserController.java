package com.financial.transaction.system.controller;

import com.financial.transaction.system.requestDTO.UserRequestDTO;
import com.financial.transaction.system.responseDTO.UserResponseDTO;
import com.financial.transaction.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/add")
    public UserResponseDTO addUser(@RequestBody UserRequestDTO userRequestDTO){

         return userService.addUser(userRequestDTO);
    }

}
