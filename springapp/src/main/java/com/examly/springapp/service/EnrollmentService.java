package com.examly.springapp.service;

import java.util.List;
import com.examly.springapp.model.dto.EnrollmentDTO;

/**
 * The EnrollmentService interface defines the contract for managing enrollments.
 * It declares methods for adding, editing, retrieving, and deleting enrollments.
 * Implementations of this interface should provide the actual business logic for these operations.
 */
public interface EnrollmentService {
     // Adds a new Enrollment
     EnrollmentDTO addEnrollment(EnrollmentDTO enrollment);

     // Edits a specific Enrollment based on Enrollment ID
     EnrollmentDTO editEnrollment(EnrollmentDTO enrolment , Long enrollmentId);

     // Retrieves all Enrollments
     List<EnrollmentDTO> getAllEnrollments();

     // Retrieves a specific Enrollment based on Enrollment ID
     EnrollmentDTO getEnrollmentById(Long enrollmentId);

     // Retrieves Enrollments for a specific user based on course ID
     List<EnrollmentDTO> getAllEnrollmentsByCourseId(Long courseId);

     // Retrieves Enrollments for a specific user based on user ID
     List<EnrollmentDTO> getAllEnrollmentsByUserId(Long userId);

     // Deletes a specific Enrollment based on Enrollment ID
     EnrollmentDTO deleteEnrollment(Long enrollmentId);

   
    
}