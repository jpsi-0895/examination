package com.shubhamjajoo.examsite.payload.request;

import com.shubhamjajoo.examsite.entity.Authority;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest {
    private String name;
    private String email;
    private String password;
    private String phone;
    private List<String> scopes;
}
