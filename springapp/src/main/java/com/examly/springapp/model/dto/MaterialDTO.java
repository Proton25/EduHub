package com.examly.springapp.model.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class MaterialDTO {
    
    private Long materialId;
    @NotBlank(message = "Title is mandatory")
    @Size(max = 100, message = "Title cannot be longer than 100 characters")
    private String title;

    @NotBlank(message = "Description is mandatory")
    @Size(max = 500, message = "Description cannot be longer than 500 characters")
    private String description;

    @NotBlank(message = "URL is mandatory")
    @Size(max = 200, message = "URL cannot be longer than 200 characters")
    private String url;

    @NotBlank(message = "Content type is mandatory")
    @Size(max = 50, message = "Content type cannot be longer than 50 characters")
    private String contentType;

    @NotNull(message = "Course ID is mandatory")
    private Long courseId;

    @NotNull(message = "Upload date is mandatory")
    private LocalDate uploadDate;

    public MaterialDTO() {
    }
  

    public MaterialDTO(Long materialId,String title,String description,String url,String contentType,Long courseId,LocalDate uploadDate) {
        this.materialId = materialId;
        this.title = title;
        this.description = description;
        this.url = url;
        this.contentType = contentType;
        this.courseId = courseId;
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

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public LocalDate getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(LocalDate uploadDate) {
        this.uploadDate = uploadDate;
    }   
    
}