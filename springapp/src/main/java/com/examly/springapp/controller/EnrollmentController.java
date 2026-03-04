package com.examly.springapp.controller;

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

import java.util.List;
import com.examly.springapp.model.dto.EnrollmentDTO;
import com.examly.springapp.service.EnrollmentServiceImpl;

import jakarta.validation.Valid;

/**
 * The EnrollmentController class handles HTTP requests for managing
 * enrollments.
 * It provides endpoints for adding, retrieving, editing, and deleting
 * enrollments.
 * This controller relies on the EnrollmentServiceImpl to perform the actual
 * operations.
 * ResponseEntity is used to provide HTTP responses with appropriate status
 * codes.
 */
@RestController
public class EnrollmentController {

    @Autowired
    private EnrollmentServiceImpl enrollmentServiceImpl;

    /**
     * Add a new enrollment.
     * 
     * @param enrollment The enrollment to add.
     * @return ResponseEntity with the created enrollment and HTTP status 201, or
     *         HTTP status 500 if creation fails.
     */
    @PostMapping("/api/enrollment")
    // @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<EnrollmentDTO> addEnrollment(@Valid @RequestBody EnrollmentDTO enrollment) {
        EnrollmentDTO obj = enrollmentServiceImpl.addEnrollment(enrollment);
        if (obj != null) {
            return ResponseEntity.status(201).body(obj);

        } else {
            return ResponseEntity.status(500).build();

        }

    }

    /**
     * Retrieve all enrollments.
     * 
     * @return ResponseEntity with a list of enrollments and HTTP status 200, or
     *         HTTP status 404 if no enrollments found.
     */
    @GetMapping("/api/enrollment")
    // @PreAuthorize("hasAnyRole('EDUCATOR','STUDENT')")
    public ResponseEntity<List<EnrollmentDTO>> getAllEnrollments() {
        List<EnrollmentDTO> obj = enrollmentServiceImpl.getAllEnrollments();
        if (!obj.isEmpty()) {
            return ResponseEntity.status(200).body(obj);
        } else {
            return ResponseEntity.status(404).build();
        }
    }

    /**
     * Retrieve a enrollment by ID.
     * 
     * @param enrollment The ID of the enrollment to retrieve.
     * @return ResponseEntity with the retrieved enorllment and HTTP status 200.
     */
    @GetMapping("/api/enrollment/{enrollmentId}")
    // @PreAuthorize("hasAnyRole('EDUCATOR','STUDENT')")
    public ResponseEntity<EnrollmentDTO> getEnrollmentById(@PathVariable Long enrollmentId) {
        EnrollmentDTO obj = enrollmentServiceImpl.getEnrollmentById(enrollmentId);
        if (obj != null) {
            return ResponseEntity.status(200).body(obj);

        } else {
            return ResponseEntity.status(404).build();
        }

    }

    /**
     * Retrieve all enrollments by user ID.
     * 
     * @param userId The user ID to search enrollments by.
     * @return ResponseEntity with a list of enrollments and HTTP status 200, or
     *         HTTP status 404 if no enrollments found.
     */
    @GetMapping("/api/enrollment/user/{userId}")
    // @PreAuthorize("hasAnyRole('EDUCATOR','STUDENT')")
    public ResponseEntity<List<EnrollmentDTO>> getAllEnrollmentsByUserId(@PathVariable Long userId) {
        List<EnrollmentDTO> obj = enrollmentServiceImpl.getAllEnrollmentsByUserId(userId);
        if (!obj.isEmpty()) {
            return ResponseEntity.status(200).body(obj);

        } else {
            return ResponseEntity.status(404).build();
        }

    }

    /**
     * Retrieve all enrollments by course ID.
     * 
     * @param courseId The course ID to search enrollments by.
     * @return ResponseEntity with a list of enrollments and HTTP status 200, or
     *         HTTP status 404 if no enrollments found.
     */
    @GetMapping("/api/enrollment/course/{courseId}")
    // @PreAuthorize("hasAnyRole('EDUCATOR','STUDENT')")
    public ResponseEntity<List<EnrollmentDTO>> getAllEnrollmentsByCourseId(@PathVariable Long courseId) {
        List<EnrollmentDTO> obj = enrollmentServiceImpl.getAllEnrollmentsByCourseId(courseId);
        if (!obj.isEmpty()) {
            return ResponseEntity.status(200).body(obj);
        } else {
            return ResponseEntity.status(404).build();
        }
    }

    /**
     * Edit an existing enrollment.
     * 
     * @param enrollment   The enrollment details to update.
     * @param enrollmentId The ID of the enrollment to edit.
     * @return ResponseEntity with the updated enrollment and HTTP status 200, or
     *         HTTP status 404 if enrollment not found.
     */

    @PutMapping("/api/enrollment/{enrollmentId}")
    // @PreAuthorize("hasRole('EDUCATOR')")
    public ResponseEntity<EnrollmentDTO> editEnrollment(@Valid @RequestBody EnrollmentDTO enrollment,
            @PathVariable Long enrollmentId) {
        EnrollmentDTO obj = enrollmentServiceImpl.editEnrollment(enrollment, enrollmentId);
        if (obj != null) {

            return ResponseEntity.status(200).body(obj);
        } else {
            return ResponseEntity.status(500).build();
        }

    }

    /**
     * Delete an enrollment by ID.
     * 
     * @param enrollmentId The ID of the enrollment to delete.
     * @return ResponseEntity with the deleted enrollment and HTTP status 200, or
     *         HTTP status 404 if enrollment not found.
     */

    @DeleteMapping("/api/enrollment/{enrollmentId}")
    // @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<EnrollmentDTO> deleteEnrollment(@PathVariable Long enrollmentId) {
        EnrollmentDTO obj = enrollmentServiceImpl.deleteEnrollment(enrollmentId);
        if (obj != null) {
            return ResponseEntity.status(200).body(obj);

        } else {
            return ResponseEntity.status(500).build();
        }

    }
}