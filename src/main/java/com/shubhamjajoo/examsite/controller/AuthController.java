package com.shubhamjajoo.examsite.controller;

import com.shubhamjajoo.examsite.entity.User;
import com.shubhamjajoo.examsite.payload.response.JWTAuthResponse;
import com.shubhamjajoo.examsite.payload.request.SignInRequest;
import com.shubhamjajoo.examsite.payload.request.SignUpRequest;
import com.shubhamjajoo.examsite.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping(value = "/signup")
    public ResponseEntity<User> userSignup (@RequestBody SignUpRequest signUpRequest) {
        User user = authService.register(signUpRequest);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<JWTAuthResponse> userLogin (@RequestBody SignInRequest signInRequest) {

        String token = authService.login(signInRequest);

        JWTAuthResponse jwtAuthResponse = new JWTAuthResponse();
        jwtAuthResponse.setAccessToken(token);

        return new ResponseEntity<>(jwtAuthResponse, HttpStatus.OK);
    }
}
