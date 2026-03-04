
package com.examly.springapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.examly.springapp.config.JwtUtils;
import com.examly.springapp.exception.UserAlreadyExistsException;
import com.examly.springapp.exception.UserNotFoundException;
import com.examly.springapp.model.AuthUser;
import com.examly.springapp.model.User;
import com.examly.springapp.model.dto.UserDTO;
import com.examly.springapp.repository.UserRepo;

/**
 * UserServiceImpl is an implementation of the UserService interface that
 * handles user-related operations.
 * It provides methods for user registration and login, utilizing JWT for
 * authentication.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JwtUtils jwtutils;

    /**
     * Registers a new user if the user does not already exist.
     * 
     * @param userDTO The UserDTO object containing user details.
     * @return The registered UserDTO object with the password set to null.
     * @throws UserAlreadyExistsException If the user already exists.
     */
    public UserDTO registerUser(UserDTO userDTO) {
        User newUser = userRepo.findByEmail(userDTO.getEmail()).orElse(null);
        if(newUser == null){
        User user = convertToEntity(userDTO);
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userRepo.save(user);
        userDTO.setUserId(user.getUserId());
        userDTO.setPassword(null); // Don't expose the password
        return userDTO;
        }
        throw new UserAlreadyExistsException("User Already Exists!");
        
    }

    /**
     * Authenticates a user and generates a JWT token.
     * 
     * @param userDTO The UserDTO object containing login credentials.
     * @return The AuthUser object containing authentication details and JWT token.
     * @throws UserNotFoundException If the user credentials are invalid.
     */
    public AuthUser loginUser(UserDTO userDTO) {
        User user = userRepo.findByEmail(userDTO.getEmail())
            .orElseThrow(() -> new UserNotFoundException("Email not found"));
        if (!passwordEncoder.matches(userDTO.getPassword(), user.getPassword())) {
             throw new UserNotFoundException("Password does'nt matched");
        }
        Authentication authentication = authManager
                .authenticate(new UsernamePasswordAuthenticationToken(userDTO.getEmail(), userDTO.getPassword()));
        if (authentication.isAuthenticated()) {
            AuthUser authUser = convertToAuthUser(user);
            authUser.setJwtToken(jwtutils.generateToken(user.getEmail(), user.getRole())); // Generate JWT token
            return authUser;
        }
        throw new UserNotFoundException("Invalid User Name or Password");
    }

    /**
     * Retrieves user details by user ID.
     * 
     * @param userId The ID of the user to retrieve.
     * @return The UserDTO object containing user details.
     * @throws UserNotFoundException If the user is not found.
     */
    public UserDTO getUserDetails(Long userId) {
        User obj = userRepo.findById(userId)
            .orElseThrow(() -> new UserNotFoundException("User not found with this ID."));
        return convertToDTO(obj);    
    }

    /**
     * Converts a UserDTO to a User entity.
     * 
     * @param userDTO The UserDTO to convert.
     * @return The converted User entity.
     */
    private User convertToEntity(UserDTO userDTO) {
        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword()); // Password encoding handled separately
        user.setUsername(userDTO.getUsername());
        user.setMobileNumber(userDTO.getMobileNumber());
        user.setRole(userDTO.getRole());
        return user;
    }

    /**
     * Converts a User entity to a UserDTO.
     * 
     * @param user The User entity to convert.
     * @return The converted UserDTO.
     */
    private UserDTO convertToDTO(User user) {
        return new UserDTO(user.getUserId(),user.getEmail(), null, user.getUsername(), user.getMobileNumber(), user.getRole());
    }

    /**
     * Converts a User entity to an AuthUser.
     * 
     * @param user The User entity to convert.
     * @return The converted AuthUser.
     */
    private AuthUser convertToAuthUser(User user) {
        AuthUser authUser = new AuthUser();
        authUser.setEmail(user.getEmail());
        authUser.setRole(user.getRole());
        authUser.setUserId(user.getUserId());
        authUser.setMobileNumber(user.getMobileNumber());
        authUser.setUsername(user.getUsername());
        return authUser;
    }
}
