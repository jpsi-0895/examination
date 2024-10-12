package com.shubhamjajoo.examsite.service;

import com.shubhamjajoo.examsite.entity.User;
import com.shubhamjajoo.examsite.payload.request.SignInRequest;
import com.shubhamjajoo.examsite.payload.request.SignUpRequest;

public interface AuthService {
    User register(SignUpRequest signUpRequest);

    User registerViaAdmin(SignUpRequest signUpRequest);

    String login(SignInRequest signInRequest);
}
