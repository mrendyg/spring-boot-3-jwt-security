package com.andyg.springboot3jwtsecurity.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    public void changePassword(ChangePasswordRequest request, Principal connectedUser){
        var user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();

        //check if the correct password is correct
        if (!passwordEncoder.matches(request.getCurrentPasword(), user.getPassword())) {
            throw new IllegalStateException("contraseña incorrecta");
        }

        //check if the two new password are the same
        if (!request.getNewPassword().equals(request.getConfirmationPassword())){
            throw new IllegalStateException("La conrtaseña no es la misma");
        }

        //update the password
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));

        //Save the new password
        userRepository.save(user);
    }

}
