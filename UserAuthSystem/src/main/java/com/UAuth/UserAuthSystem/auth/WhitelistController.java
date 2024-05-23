package com.UAuth.UserAuthSystem.auth;

import com.UAuth.UserAuthSystem.controller.AuthenticationRequest;
import com.UAuth.UserAuthSystem.controller.AuthenticationResponse;
import com.UAuth.UserAuthSystem.controller.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/whitelist")
@RequiredArgsConstructor
public class WhitelistController {

    private final WhitelistService service;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request)
    {
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request)
    {
        return ResponseEntity.ok(service.authenticate(request));
    }

    @PutMapping("/reset/{id}")
    public String resetPassword(@PathVariable String id, @RequestBody String password)
    {
        return service.resetPassword(id, password);
    }
}
