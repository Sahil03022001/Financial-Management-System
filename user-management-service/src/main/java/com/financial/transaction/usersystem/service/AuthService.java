package com.financial.transaction.usersystem.service;

import com.financial.transaction.system.requests.LoginRequest;
import com.financial.transaction.system.response.AuthResponse;
import com.financial.transaction.usersystem.entity.User;
import com.financial.transaction.usersystem.repository.UserRepository;
import com.financial.transaction.usersystem.utils.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private static final Logger LOG = LoggerFactory.getLogger(AuthService.class);

    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    PasswordEncoder passwordEncoder;

    public AuthResponse login(LoginRequest request) throws Exception {

        if (request.getUserId() == null || request.getPassword() == null) {
            throw new Exception("UserId or Password cannot be null");
        }

        User user = userRepository.findByUserId(request.getUserId());
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        Authentication authentication = authenticate(request.getUserId(), request.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtUtil.generateToken(user.getUserId());

        AuthResponse response = new AuthResponse(token, "Login Success");

        return response;
    }

    private Authentication authenticate(String userId, String password) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(userId);

        if (userDetails == null) {
            throw new BadCredentialsException("Invalid Username...");
        }

        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Invalid password...");
        }

        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
}
