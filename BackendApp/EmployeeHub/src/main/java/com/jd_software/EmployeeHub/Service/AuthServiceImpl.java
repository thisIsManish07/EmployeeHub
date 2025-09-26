package com.jd_software.EmployeeHub.Service;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.jd_software.EmployeeHub.DTOs.AuthResponse;
import com.jd_software.EmployeeHub.DTOs.LoginRequest;
import com.jd_software.EmployeeHub.DTOs.RegisterRequest;
import com.jd_software.EmployeeHub.DTOs.UserInfo;
import com.jd_software.EmployeeHub.Entity.User;
import com.jd_software.EmployeeHub.Repository.UserRepository;
import com.jd_software.EmployeeHub.Security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Collections;
import java.util.Optional;
@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtUtil jwtUtil;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public String register(RegisterRequest request) {
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole() != null ? request.getRole() : "USER");

        userRepository.save(user);
        return "User registered successfully!";
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        Optional<User> userOpt = userRepository.findByEmail(request.getEmail());
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            if (passwordEncoder.matches(request.getPassword(), user.getPassword())) {
                String token = jwtUtil.generateToken(user.getEmail(), user.getRole());
                return new AuthResponse(
                        "Login successful",
                        token,
                        Instant.now().plusMillis(3600000), // 1 hour expiry
                        new UserInfo(user.getId(), user.getName(), user.getEmail(), user.getRole()) // ✅ add this
                );
            }
        }
        return new AuthResponse("Invalid credentials", null, null, null);
    }


    @Override
    public AuthResponse loginWithGoogle(String idTokenString) {
        try {
            GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier
                    .Builder(new NetHttpTransport(), GsonFactory.getDefaultInstance())
                    .setAudience(Collections.singletonList("380194766677-hlb2g4k2da5nt9nsv3ovmf1gc616jvoj.apps.googleusercontent.com"))
                    .build();

            GoogleIdToken idToken = verifier.verify(idTokenString);
            if (idToken != null) {
                GoogleIdToken.Payload payload = idToken.getPayload();

                String email = payload.getEmail();
                String name = (String) payload.get("name");

                // check if user already exists
                User user = userRepository.findByEmail(email).orElseGet(() -> {
                    User newUser = new User();
                    newUser.setName(name);
                    newUser.setEmail(email);
                    newUser.setPassword(""); // no password for google accounts
                    newUser.setRole("USER"); // default
                    return userRepository.save(newUser);
                });

                // generate JWT
                String token = jwtUtil.generateToken(user.getEmail(), user.getRole());

                return new AuthResponse(
                        "Google login successful",
                        token,
                        Instant.now().plusMillis(3600000),
                        new UserInfo(user.getId(), user.getName(), user.getEmail(), user.getRole())
                );
            } else {
                return new AuthResponse("Invalid Google token", null, null, null);
            }
        } catch (Exception e) {
            return new AuthResponse("Error verifying Google token", null, null, null);
        }
    }
}
