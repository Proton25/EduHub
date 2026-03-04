package com.examly.springapp.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.examly.springapp.model.Feedback;

/**
 * Repository interface for managing Feedback entities.
 * This interface extends JpaRepository, providing CRUD operations and
 * custom query methods for Feedback entities.
 */
@Repository
public interface FeedbackRepo extends JpaRepository<Feedback, Long> {

    @Query("SELECT f FROM Feedback f WHERE f.user.userId = :userId")
    List<Feedback> findAllFeedbacksByUserId(Long userId);
    

    @Query("SELECT f FROM Feedback f")
    List<Feedback> findAllFeedbacks();
}