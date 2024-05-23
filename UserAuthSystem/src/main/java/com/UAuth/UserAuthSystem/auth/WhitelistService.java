package com.UAuth.UserAuthSystem.auth;

import com.UAuth.UserAuthSystem.controller.AuthenticationRequest;
import com.UAuth.UserAuthSystem.controller.AuthenticationResponse;
import com.UAuth.UserAuthSystem.controller.RegisterRequest;
import com.UAuth.UserAuthSystem.model.User;
import com.UAuth.UserAuthSystem.repository.Repository;
import com.UAuth.UserAuthSystem.config.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WhitelistService {

    private final Repository repository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .userName(request.getUserName())
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .role(request.getRole())
                .build();
        if(repository.findByUserName(user.getUsername())!=null)
        {
            return new AuthenticationResponse("User already exists");
        }
        repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUserName(),
                        request.getPassword()
                )
        );
        var user = repository.findByUserName(request.getUserName());
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public String resetPassword(String userName, String password)
    {
        if(userName==null)
        {
            return "Username is Empty";
        }
        User oldUser = repository.findByUserName(userName);
        if(oldUser==null)
        {
            return "No User Found";
        }
        try {

            oldUser.setPassword(passwordEncoder.encode(password));
            repository.save(oldUser);
            return "Password Reset Successful";
        }
        catch (Exception e) {
            return "Failed to reset password: " + e.getMessage();
        }
    }
}
