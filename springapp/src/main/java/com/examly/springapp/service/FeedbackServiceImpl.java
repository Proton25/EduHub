package com.examly.springapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springapp.exception.FeedbackNotFoundException;
import com.examly.springapp.exception.UserNotFoundException;
import com.examly.springapp.model.Feedback;
import com.examly.springapp.model.User;
import com.examly.springapp.model.dto.FeedbackDTO;
import com.examly.springapp.repository.FeedbackRepo;
import com.examly.springapp.repository.UserRepo;

/* @Service Marks this class as a Spring service component that contains business logic */
@Service
public class FeedbackServiceImpl implements FeedbackService {
    
    @Autowired
    private FeedbackRepo feedbackRepo;

    @Autowired
    private UserRepo userRepo;

    @Override
    public FeedbackDTO addFeedback(FeedbackDTO feedbackDTO) {
        User userObj = userRepo.findById(feedbackDTO.getUserId())
            .orElseThrow(() -> new UserNotFoundException("User not found."));
            Feedback feedback = toEntity(feedbackDTO, userObj);
            Feedback savedFeedback = feedbackRepo.save(feedback);
            return toDTO(savedFeedback);    
    }

    @Override
    public List<FeedbackDTO> getFeedback() {
        List<Feedback> feedbackList = feedbackRepo.findAllFeedbacks();
        List<FeedbackDTO> feedbackDTOList = new ArrayList<>();
        for (Feedback feedback : feedbackList) {
            FeedbackDTO feedbackDTO = toDTO(feedback);
            feedbackDTOList.add(feedbackDTO);
        }
        return feedbackDTOList;
    }

    @Override
    public FeedbackDTO deleteFeedback(Long feedbackId) {
        Feedback feedbackObj = feedbackRepo.findById(feedbackId)
            .orElseThrow(() -> new FeedbackNotFoundException("Feedback not found."));
        feedbackRepo.deleteById(feedbackId);
        return toDTO(feedbackObj);   
    }

    @Override
    public List<FeedbackDTO> getFeedbackByUserId(Long userId) {
        if (!userRepo.existsById(userId)){
            throw new UserNotFoundException("User not found with this ID.");
        }
        List<Feedback> feedbackList = feedbackRepo.findAllFeedbacksByUserId(userId);
        List<FeedbackDTO> feedbackDTOList = new ArrayList<>();
        for (Feedback feedback : feedbackList) {
            FeedbackDTO feedbackDTO = toDTO(feedback);
            feedbackDTOList.add(feedbackDTO);
        }
        return feedbackDTOList;
    }

    // Convert from DTO to Entity
    private Feedback toEntity(FeedbackDTO feedbackDTO, User user) {
        return new Feedback(feedbackDTO.getFeedbackId(), feedbackDTO.getFeedbackText(),
                feedbackDTO.getDate(), user);
    }

    // Convert from Entity to DTO
    private FeedbackDTO toDTO(Feedback feedback) {
        return new FeedbackDTO(feedback.getFeedbackId(), feedback.getFeedbackText(),
                feedback.getDate(), feedback.getUser().getUserId());
    }
}