package com.andyg.springboot3jwtsecurity.auth;

import com.andyg.springboot3jwtsecurity.user.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String fistname;
    private String lastname;
    private String email;
    private String password;
    private Role role;

}
