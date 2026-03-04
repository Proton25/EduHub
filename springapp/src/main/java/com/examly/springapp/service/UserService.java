package com.examly.springapp.service;

import com.examly.springapp.model.AuthUser;
import com.examly.springapp.model.dto.UserDTO;

/**
 * The UserService interface defines the contract for user-related operations.
 * It provides methods for registering a new user and logging in an existing
 * user.
 */
public interface UserService {
    /**
     * Registers a new user.
     * 
     * @param user The user object containing user details.
     * @return The registered user object.
     */
    UserDTO registerUser(UserDTO userDTO);

    /**
     * Logs in an existing user.
     * 
     * @param user The user object containing login details.
     * @return The logged-in user object.
     */
    AuthUser loginUser(UserDTO user);

    // UserDTO getUserDetailsByUserEmail(String userEmail);

    UserDTO getUserDetails(Long userId);
    

}
