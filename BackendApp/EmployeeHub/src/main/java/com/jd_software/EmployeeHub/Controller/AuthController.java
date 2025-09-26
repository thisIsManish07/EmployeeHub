package com.jd_software.EmployeeHub.Controller;

import com.jd_software.EmployeeHub.DTOs.AuthResponse;
import com.jd_software.EmployeeHub.DTOs.LoginRequest;
import com.jd_software.EmployeeHub.DTOs.RegisterRequest;
import com.jd_software.EmployeeHub.Entity.User;
import com.jd_software.EmployeeHub.Service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public String register(@Valid @RequestBody RegisterRequest registerRequest) {
        return authService.register(registerRequest);
    }

    @PostMapping("/login")
    public AuthResponse login(@Valid @RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }
    @PostMapping("/google")
    public AuthResponse loginWithGoogle(@RequestBody Map<String, String> request) {
        String idToken = request.get("idToken");
        return authService.loginWithGoogle(idToken);
    }

}
