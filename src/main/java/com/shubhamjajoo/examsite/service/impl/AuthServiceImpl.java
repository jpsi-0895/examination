package com.shubhamjajoo.examsite.service.impl;

import com.shubhamjajoo.examsite.entity.Authority;
import com.shubhamjajoo.examsite.entity.User;
import com.shubhamjajoo.examsite.exception.APIException;
import com.shubhamjajoo.examsite.exception.ResourceNotFoundException;
import com.shubhamjajoo.examsite.payload.request.SignInRequest;
import com.shubhamjajoo.examsite.payload.request.SignUpRequest;
import com.shubhamjajoo.examsite.repository.AuthorityRepository;
import com.shubhamjajoo.examsite.repository.UserRepository;
import com.shubhamjajoo.examsite.security.JWTTokenProvider;
import com.shubhamjajoo.examsite.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTTokenProvider jwtTokenProvider;

    @Override
    public User register(SignUpRequest signUpRequest) {
        if(userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new APIException(HttpStatus.CONFLICT, "User Already Exist");
        }

        User user = new User();
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setName(signUpRequest.getName());

        List<Authority> authorities = new ArrayList<>();

        authorities.add( authorityRepository.findByName("ROLE_USER").orElseThrow(() ->
                new ResourceNotFoundException("Scope", "ROLE_USER")));

        user.setAuthorities(authorities);

        userRepository.save(user);

        return user;
    }

    @Override
    public User registerViaAdmin(SignUpRequest signUpRequest) {
        if(userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new APIException(HttpStatus.CONFLICT, "User Already Exist");
        }

        User user = new User();
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setName(signUpRequest.getName());

        if(!signUpRequest.getScopes().isEmpty()){
            List<Authority> authorities = signUpRequest.getScopes().stream().map((auth) ->
                    authorityRepository.findByName(auth).orElseThrow(() ->
                            new ResourceNotFoundException("Scopes", auth)
                    )).collect(Collectors.toList());
            user.setAuthorities(authorities);
        }
        else {
            throw new APIException(HttpStatus.INTERNAL_SERVER_ERROR, "Scopes not available");
        }

        userRepository.save(user);

        return user;
    }

    @Override
    public String login(SignInRequest signInRequest) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        signInRequest.getEmail(),
                        signInRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return jwtTokenProvider.generateToken(authentication);
    }
}
