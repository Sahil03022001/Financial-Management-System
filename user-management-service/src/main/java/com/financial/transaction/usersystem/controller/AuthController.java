package com.financial.transaction.usersystem.controller;

import com.financial.transaction.system.requests.LoginRequest;
import com.financial.transaction.system.requests.RegisterRequest;
import com.financial.transaction.system.requests.UserRequestDTO;
import com.financial.transaction.system.response.AuthResponse;
import com.financial.transaction.system.response.UserResponseDto;
import com.financial.transaction.usersystem.service.AuthService;
import com.financial.transaction.usersystem.service.UserService;
import com.financial.transaction.usersystem.utils.JwtUtil;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private static final Logger LOG = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody @Valid RegisterRequest request) {
        UserResponseDto response = new UserResponseDto();
        try {
            //encode password first before sending create user request
            request.setPassword(passwordEncoder.encode(request.getPassword()));

            UserRequestDTO userRequestDTO = new UserRequestDTO();
            userRequestDTO.setFirstName(request.getFirstName());
            userRequestDTO.setLastName(request.getLastName());
            userRequestDTO.setEmail(request.getEmail());
            userRequestDTO.setMobNo(request.getMobNo());
            userRequestDTO.setDateOfBirth(request.getDateOfBirth());
            userRequestDTO.setPassword(request.getPassword());
            response = userService.addUser(userRequestDTO);

            if (response.isSuccessful()) {
                AuthResponse authResponse = new AuthResponse();
                Authentication authentication = new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());
                SecurityContextHolder.getContext().setAuthentication(authentication);
                String token = jwtUtil.generateToken(response.getUserId());
                authResponse.setJwt(token);
                authResponse.setMessage("Signup Success");
                return ResponseEntity.ok(authResponse);
            } else {
                AuthResponse authResponse = new AuthResponse();
                authResponse.setSuccessful(false);
                authResponse.getErrors().add("Error in registering user");
                return new ResponseEntity<>(new AuthResponse(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            LOG.error("Exception while creating User: {}", e.getMessage());
            AuthResponse authResponse = new AuthResponse();
            authResponse.setSuccessful(false);
            authResponse.getErrors().add("Error in registering user");
            return new ResponseEntity<>(new AuthResponse(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        try {
            AuthResponse response = authService.login(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            LOG.error("Error while login: {}", e.getMessage());
            return new ResponseEntity<>(new AuthResponse(), HttpStatus.BAD_REQUEST);
        }
    }
}
