package com.examly.springapp.model;

import com.examly.springapp.model.dto.UserDTO;

/**
 * AuthUser is a model class representing the authenticated user.
 * It contains user details such as userId, userName, jwtToken, role, and name.
 */
public class AuthUser extends UserDTO {

    private String jwtToken;

    public AuthUser() {
        super();
    }

    public AuthUser(Long userId, String email, String password, String username, String mobileNumber, String role,
            String jwtToken) {
        super(userId,email, password, username, mobileNumber, role);
        // this.userId = userId;
        this.jwtToken = jwtToken;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

}
