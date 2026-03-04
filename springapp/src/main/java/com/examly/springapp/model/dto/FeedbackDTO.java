package com.examly.springapp.model.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

public class FeedbackDTO {
    private Long feedbackId;

    @NotBlank(message = "Feedback text is mandatory")
    @Size(max = 500, message = "Feedback text cannot be longer than 500 characters")
    private String feedbackText;

    @NotNull(message = "Date is mandatory")
    @PastOrPresent(message = "Date cannot be in the future")
    private LocalDate date;

    @NotNull(message = "User ID is mandatory")
    private Long userId;

    public FeedbackDTO() {
    }

    public FeedbackDTO(Long feedbackId,String feedbackText, LocalDate date, Long userId) {
        this.feedbackId=feedbackId;
        this.feedbackText = feedbackText;
        this.date = date;
        this.userId = userId;
    }
    public Long getFeedbackId()
    {
        return feedbackId;
    }
    
    public void setFeedbackId(Long feedbackId)
    {
        this.feedbackId=feedbackId;
    }

    public String getFeedbackText() {
        return feedbackText;
    }

    public void setFeedbackText(String feedbackText) {
        this.feedbackText = feedbackText;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}