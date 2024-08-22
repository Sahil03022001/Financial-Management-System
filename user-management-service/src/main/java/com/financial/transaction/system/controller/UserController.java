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
    public UserResponseDTO addUser(@Valid @RequestBody UserRequestDTO userRequestDTO) throws MobileNumberAlreadyExistsException, EmailAlreadyExistsException {

         return userService.addUser(userRequestDTO);
    }

    @DeleteMapping("/delete")
    public String deleteUser(@RequestParam int id)  {

        try {
            return userService.deleteUser(id);
        } catch (UserDoesNotExist e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/getbyId")
    public UserResponseDTO getById(@RequestParam int id) throws UserDoesNotExist {

        return userService.getById(id);
    }

}
