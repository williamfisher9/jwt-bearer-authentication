package com.apps.securityapp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.Set;

public class SignUpDTO {
    @NotBlank(message = "Username field is required!")
    private String username;

    @NotBlank(message = "Password field is required!")
    private String password;

    @NotBlank(message = "Email address field is required!")
    private String email;

    @NotBlank(message = "Full name field is required!")
    private String fullName;

    @NotEmpty(message = "Roles list should not be empty!")
    private Set<String> roles;

    public SignUpDTO(String username, String password, String email, String fullName, Set<String> roles) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.fullName = fullName;
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
}
