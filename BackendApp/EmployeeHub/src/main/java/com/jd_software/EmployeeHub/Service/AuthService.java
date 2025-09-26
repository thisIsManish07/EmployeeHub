package com.jd_software.EmployeeHub.Service;

import com.jd_software.EmployeeHub.DTOs.AuthResponse;
import com.jd_software.EmployeeHub.DTOs.LoginRequest;
import com.jd_software.EmployeeHub.DTOs.RegisterRequest;
import com.jd_software.EmployeeHub.Entity.User;

public interface AuthService {
    String register(RegisterRequest request);
    AuthResponse login(LoginRequest request);
    AuthResponse loginWithGoogle(String idTokenString);

}
