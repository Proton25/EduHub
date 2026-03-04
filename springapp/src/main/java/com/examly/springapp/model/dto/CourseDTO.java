package com.examly.springapp.model.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CourseDTO {
    
    private Long courseId;

    @NotBlank(message = "Title is mandatory")
    @Size(max = 100, message = "Title cannot be longer than 100 characters")
    private String title;

    @NotBlank(message = "Description is mandatory")
    @Size(max = 500, message = "Description cannot be longer than 500 characters")
    private String description;

    @NotNull(message = "Start date is mandatory")
    @FutureOrPresent(message = "Start date cannot be in the past")
    private LocalDate courseStartDate;

    @NotNull(message = "End date is mandatory")
    @FutureOrPresent(message = "End date cannot be in the past")
    private LocalDate courseEndDate;

    @NotBlank(message = "Category is mandatory")
    @Size(max = 50, message = "Category cannot be longer than 50 characters")
    private String category;

    @NotBlank(message = "Level is mandatory")
    @Size(max = 50, message = "Level cannot be longer than 50 characters")
    private String level;

    public CourseDTO() {
    }

    public CourseDTO(Long courseId,String title, String description, LocalDate courseStartDate, LocalDate courseEndDate,
            String category, String level) {
        this.courseId=courseId;
        this.title = title;
        this.description = description;
        this.courseStartDate = courseStartDate;
        this.courseEndDate = courseEndDate;
        this.category = category;
        this.level = level;
    }
    public Long getCourseId()
    {
        return courseId;
    }
    public void setCourseId(Long courseId)
    {
        this.courseId=courseId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getCourseStartDate() {
        return courseStartDate;
    }

    public void setCourseStartDate(LocalDate courseStartDate) {
        this.courseStartDate = courseStartDate;
    }

    public LocalDate getCourseEndDate() {
        return courseEndDate;
    }

    public void setCourseEndDate(LocalDate courseEndDate) {
        this.courseEndDate = courseEndDate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}