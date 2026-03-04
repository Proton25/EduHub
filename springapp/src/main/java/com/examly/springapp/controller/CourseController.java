package com.examly.springapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.examly.springapp.model.dto.CourseDTO;
import com.examly.springapp.service.CourseServiceImpl;

import jakarta.validation.Valid;

/**
 * The CourseController class handles HTTP requests for managing courses.
 * It provides endpoints for adding, retrieving, editing, and deleting courses.
 * This controller relies on the CourseServiceImpl to perform the actual
 * operations.
 * ResponseEntity is used to provide HTTP responses with appropriate status
 * codes.
 */
@RestController
public class CourseController {

    @Autowired
    private CourseServiceImpl courseService;

    /**
     * Add a new course.
     * 
     * @param course The course to add.
     * @return ResponseEntity with the created course and HTTP status 201.
     */
    @PostMapping("/api/course")
    // @PreAuthorize("hasRole('EDUCATOR')")
    public ResponseEntity<CourseDTO> addCourse(@Valid @RequestBody CourseDTO courseDTO) {
        CourseDTO courseObj = courseService.addCourse(courseDTO);
        if (courseDTO != null) {
            return ResponseEntity.status(201).body(courseObj);

        } else {
            return ResponseEntity.status(500).build();
        }
    }

    /**
     * Retrieve all courses.
     * 
     * @return ResponseEntity with a list of all courses and HTTP status 200.
     */
    @GetMapping("/api/course")
//    @PreAuthorize("hasAnyRole('EDUCATOR',STUDENT)")
    public ResponseEntity<List<CourseDTO>> getAllCourses() {
        List<CourseDTO> course = courseService.getAllCourses();
        if (course != null) {
            return ResponseEntity.status(200).body(course);

        } else {
            return ResponseEntity.status(500).build();
        }
    }

    /**
     * Retrieve a course by ID.
     * 
     * @param courseId The ID of the course to retrieve.
     * @return ResponseEntity with the retrieved course and HTTP status 200.
     */
    @GetMapping("/api/course/{courseId}")
//    @PreAuthorize("hasAnyRole('EDUCATOR','STUDENT')")
    public ResponseEntity<CourseDTO> getCourseById(@PathVariable Long courseId) {
        CourseDTO course = courseService.getCourseById(courseId);
        if (course != null) {
            return ResponseEntity.status(200).body(course);

        } else {
            return ResponseEntity.status(500).build();

        }
    }

    /**
     * Edit an existing course.
     * 
     * @param courseId The ID of the course to edit.
     * @param course   The updated course details.
     * @return ResponseEntity with the edited course and HTTP status 200.
     */
    @PutMapping("/api/course/{courseId}")
    // @PreAuthorize("hasRole('EDUCATOR')")
    public ResponseEntity<CourseDTO> editCourse(@PathVariable Long courseId, @Valid @RequestBody CourseDTO courseDTO) {
        CourseDTO course = courseService.editCourse(courseId, courseDTO);
        if (course != null) {
            return ResponseEntity.status(200).body(course);

        } else {
            return ResponseEntity.status(500).build();

        }
    }

    /**
     * Delete a course by ID.
     * 
     * @param courseId The ID of the course to delete.
     * @return ResponseEntity with the deleted course and HTTP status 200.
     */
    @DeleteMapping("/api/course/{courseId}")
    // @PreAuthorize("hasRole('EDUCATOR')")
    public ResponseEntity<CourseDTO> deleteCourse(@PathVariable Long courseId) {
        CourseDTO course = courseService.deleteCourse(courseId);
        if (course != null) {
            return ResponseEntity.status(200).body(course);

        } else {
            return ResponseEntity.status(500).build();

        }
    }
}