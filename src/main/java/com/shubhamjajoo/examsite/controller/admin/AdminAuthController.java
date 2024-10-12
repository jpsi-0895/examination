package com.shubhamjajoo.examsite.controller.admin;

import com.shubhamjajoo.examsite.entity.User;
import com.shubhamjajoo.examsite.payload.request.SignUpRequest;
import com.shubhamjajoo.examsite.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/api/auth")
public class AdminAuthController {

    @Autowired
    private AuthService authService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "/register")
    public ResponseEntity<User> userRegister (@RequestBody SignUpRequest signUpRequest) {
        User user = authService.registerViaAdmin(signUpRequest);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
