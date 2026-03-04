package com.examly.springapp.model;
import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Represents a course offered in the application.
 * Each course has a unique identifier, a title, a description, 
 * start and end dates, a category, and a level.
 */
@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long courseId;
    private String title;
    private String description;
    private LocalDate courseStartDate;
    private LocalDate courseEndDate;
    private String category;
    private String level;
    public Course(){}
    public Course(Long courseId, String title, String description, LocalDate courseStartDate, LocalDate courseEndDate,
            String category, String level) {
        this.courseId = courseId;
        this.title = title;
        this.description = description;
        this.courseStartDate = courseStartDate;
        this.courseEndDate = courseEndDate;
        this.category = category;
        this.level = level;
    }
    public Long getCourseId() {
        return courseId;
    }
    public void setCourseId(Long courseId) {
        this.courseId = courseId;
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
