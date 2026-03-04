package com.examly.springapp.service;

import java.util.List;
import com.examly.springapp.model.dto.FeedbackDTO;

// FeedbackService interface defines the methods for feedback-related operations
public interface FeedbackService {

    // Adds a new feedback
    FeedbackDTO addFeedback(FeedbackDTO feedback);

    // Retrieves all feedbacks
    List<FeedbackDTO> getFeedback();

    // Retrieves feedbacks for a specific user based on user ID
    List<FeedbackDTO> getFeedbackByUserId(Long userId);

    // Deletes a specific feedback based on feedback ID
    FeedbackDTO deleteFeedback(Long feedbackId);

}