package com.examly.springapp.model;

import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

/**
 * Represents a material associated with a course in the application.
 * Each material has a unique identifier, a title, a description,
 * a URL (currently stored as LocalDate, should be String), a content type,
 * the course it belongs to, and the upload date.
 */
@Entity
public class Material {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long materialId;
    private String title;
    private String description;
    private String url;
    private String contentType;
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
    private LocalDate uploadDate;

    public Material() {
    }

    public Material(Long materialId, String title, String description, String url, String contentType, Course course,
            LocalDate uploadDate) {
        this.materialId = materialId;
        this.title = title;
        this.description = description;
        this.url = url;
        this.contentType = contentType;
        this.course = course;
        this.uploadDate = uploadDate;
    }

    public Long getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Long materialId) {
        this.materialId = materialId;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public LocalDate getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(LocalDate uploadDate) {
        this.uploadDate = uploadDate;
    }

   
}
