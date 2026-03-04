package com.examly.springapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.examly.springapp.model.User;

/**
 * The UserRepo interface is a repository for performing CRUD operations
 * on User entities. It extends JpaRepository to inherit standard CRUD
 * and JPA-related methods.
 * 
 * Annotated with @Repository to indicate that it's a Spring Data repository.
 */
@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
    
    @Query("SELECT u FROM User u WHERE u.userId = :userId")
    User findByUserId(Long userId);

    
}