package com.example.botheroku.service;

import com.example.botheroku.entity.User;
import com.example.botheroku.payload.ApiResponse;
import com.example.botheroku.payload.LoginDto;
import com.example.botheroku.repository.UserRepository;
import com.example.botheroku.security.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtProvider jwtProvider;

    public ApiResponse login(LoginDto loginDto) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginDto.getUsername(),
                    loginDto.getPassword()
            ));
            User user = (User) authentication.getPrincipal();
            String token = jwtProvider.generateToken(loginDto.getUsername());
            return new ApiResponse("TOken", true, token);
        } catch (Exception e) {
            return new ApiResponse("Parol yamasa login qa`te", false);
        }
    }

}
