package com.examly.springapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.examly.springapp.model.dto.FeedbackDTO;
import com.examly.springapp.service.FeedbackService;

import jakarta.validation.Valid;

// Combines @Controller and @ResponseBody, making the class a REST controller where every method returns a JSON response
@RestController
// Base URL path for all endpoints in this controller
@RequestMapping("/api")
public class FeedbackController {

    // Automatically injects the FeedbackService dependency
    @Autowired
    private FeedbackService feedbackService;

    // Handles GET requests to retrieve all feedbacks
    @GetMapping("/feedback")
    // @PreAuthorize("hasAnyRole('EDUCATOR','STUDENT')")
    public ResponseEntity<List<FeedbackDTO>> getFeedbackAll() {
        List<FeedbackDTO> feedbacksObj = feedbackService.getFeedback();
        if (feedbacksObj != null) {
            return ResponseEntity.status(200).body(feedbacksObj);
        } else {
            return ResponseEntity.status(500).build();
        }

    }

    // Handles POST requests to add new feedback
    @PostMapping("/feedback")
    // @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<FeedbackDTO> addFeedback(@Valid @RequestBody FeedbackDTO feedback) {
        FeedbackDTO feedbackObj = feedbackService.addFeedback(feedback);
        if (feedbackObj != null) {
            return ResponseEntity.status(201).body(feedbackObj);
        } else {
            return ResponseEntity.status(500).build();
        }

    }

    // Handles GET requests to retrieve feedbacks for a specific user
    @GetMapping("/feedback/user/{userId}")
    // @PreAuthorize("hasAnyRole('EDUCATOR','STUDENT')")
    public ResponseEntity<List<FeedbackDTO>> getFeedbackByUserId(@PathVariable Long userId) {
        List<FeedbackDTO> feedbacksObj = feedbackService.getFeedbackByUserId(userId);
        if (feedbacksObj != null) {
            return ResponseEntity.status(200).body(feedbacksObj);
        } else {
            return ResponseEntity.status(404).build();
        }

    }

    // Handles DELETE requests to remove a specific feedback
    @DeleteMapping("/feedback/{feedbackId}")
    // @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<FeedbackDTO> deleteFeedback(@PathVariable Long feedbackId) {
        FeedbackDTO feedbackObj = feedbackService.deleteFeedback(feedbackId);
        if (feedbackObj != null) {
            return ResponseEntity.status(200).body(feedbackObj);
        } else {
            return ResponseEntity.status(500).build();

        }

    }

}