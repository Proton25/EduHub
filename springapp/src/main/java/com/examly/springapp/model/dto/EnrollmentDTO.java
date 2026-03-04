package com.examly.springapp.model.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class EnrollmentDTO {
    private Long enrollmentId;

    @NotNull(message = "User ID is mandatory")
    private Long userId;

    @NotNull(message = "Course ID is mandatory")
    private Long courseId;

    @NotNull(message = "Enrollment date is mandatory")
    @FutureOrPresent(message = "Enrollment date cannot be in the past")
    private LocalDate enrollmentDate;

    @Size(max = 50, message = "Status cannot be longer than 50 characters")
    private String status;

    public EnrollmentDTO() {
    }

    public EnrollmentDTO(Long enrollmentId,Long userId, Long courseId, LocalDate enrollmentDate, String status) {
        this.enrollmentId=enrollmentId;
        this.userId = userId;
        this.courseId = courseId;
        this.enrollmentDate = enrollmentDate;
        this.status = status;
    }
    public Long getEnrollmentId()
    {
        return enrollmentId;
    }

    public void getEnrollmentId(Long enrollmentId)
    {
        this.enrollmentId=enrollmentId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public LocalDate getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(LocalDate enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}