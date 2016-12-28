package com.malalaoshi.android.core.entity;

/**
 * Created by kang on 16/12/27.
 */

public class AuthUser {
    private String token;
    private String phone;
    private String name;
    private String userId;
    private String role;

    public AuthUser() {
    }

    public AuthUser(String token, String phone, String name, String userId, String role) {
        this.token = token;
        this.phone = phone;
        this.name = name;
        this.userId = userId;
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
