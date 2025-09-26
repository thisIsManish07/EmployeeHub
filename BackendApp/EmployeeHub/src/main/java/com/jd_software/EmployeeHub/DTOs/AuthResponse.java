package com.jd_software.EmployeeHub.DTOs;

import java.time.Instant;

public class AuthResponse {
    private String message;
    private String token;
    private Instant expiresAt;
    private UserInfo user;

    public AuthResponse(String message, String token, Instant expiresAt, UserInfo user) {
        this.message = message;
        this.token = token;
        this.expiresAt = expiresAt;
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Instant getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(Instant expiresAt) {
        this.expiresAt = expiresAt;
    }

    public UserInfo getUser() {
        return user;
    }

    public void setUser(UserInfo user) {
        this.user = user;
    }
}
