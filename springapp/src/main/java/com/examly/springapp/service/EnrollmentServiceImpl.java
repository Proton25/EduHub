
package com.examly.springapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import com.examly.springapp.exception.CourseNotFoundException;
import com.examly.springapp.exception.EnrollmentAlreadyExistsException;
import com.examly.springapp.exception.EnrollmentNotFoundException;
import com.examly.springapp.exception.UserNotFoundException;
import com.examly.springapp.model.Course;
import com.examly.springapp.model.Enrollment;
import com.examly.springapp.model.User;
import com.examly.springapp.model.dto.EnrollmentDTO;
import com.examly.springapp.repository.CourseRepo;
import com.examly.springapp.repository.EnrollmentRepo;
import com.examly.springapp.repository.UserRepo;

/**
 * EnrollmentServiceImpl provides implementations for EnrollmentService
 * interface.
 * It manages the business logic for adding, editing, retrieving, and deleting
 * enrollments.
 * This service relies on EnrollmentRepo for database interactions and throws
 * custom exceptions
 * for handling enrollment-related errors.
 */
@Service
public class EnrollmentServiceImpl implements EnrollmentService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CourseRepo courseRepo;

    @Autowired
    private EnrollmentRepo enrollmentRepo;

    /**
     * Adds a new enrollment if it does not already exist.
     * 
     * @param enrollmentDTO The enrollment to add.
     * @return The added enrollment.
     * @throws EnrollmentAlreadyExistsException if the enrollment already exists.
     */
    @Override
    public EnrollmentDTO addEnrollment(EnrollmentDTO enrollmentDTO) {
        Course courseObj = courseRepo.findById(enrollmentDTO.getCourseId())
               .orElseThrow(() -> new CourseNotFoundException("Course not found."));
        User userObj = userRepo.findById(enrollmentDTO.getUserId())
               .orElseThrow(() -> new UserNotFoundException("User not found."));
        Enrollment enrollmentObj=enrollmentRepo.findByUserIdCourseId(enrollmentDTO.getUserId(),enrollmentDTO.getCourseId()).orElse(null);
        if(enrollmentObj == null){
            Enrollment enrollment = convertToEntity(enrollmentDTO, userObj, courseObj);
        Enrollment savedEnrollment = enrollmentRepo.save(enrollment);
        return convertToDTO(savedEnrollment);
        }
        throw new EnrollmentAlreadyExistsException("Already enrolled to the course by the user.");
        
    }

    /**
     * Edits an existing enrollment by ID.
     * 
     * @param enrollmentDTO The updated enrollment details.
     * @param enrollmentId  The ID of the enrollment to edit.
     * @return The edited enrollment.
     * @throws EnrollmentNotFoundException if the enrollment is not found.
     */
    @Override
    public EnrollmentDTO editEnrollment(EnrollmentDTO enrollmentDTO, Long enrollmentId) {
        Enrollment enrollment = enrollmentRepo.findById(enrollmentId)
            .orElseThrow(()->new EnrollmentNotFoundException("Enrollment not found!"));
        enrollment.setEnrollmentDate(enrollmentDTO.getEnrollmentDate());
        enrollment.setStatus(enrollmentDTO.getStatus());
        Enrollment updatedEnrollment = enrollmentRepo.save(enrollment);
        return convertToDTO(updatedEnrollment);
    }

    /**
     * Retrieves all enrollments.
     * 
     * @return A list of all enrollments.
     */
    @Override
    public List<EnrollmentDTO> getAllEnrollments() {
        List<Enrollment> enrollmentList = enrollmentRepo.findAllEnrollments();
        List<EnrollmentDTO> enrollmentDTOList = new ArrayList<>();
        for (Enrollment enrollment : enrollmentList) {
            EnrollmentDTO enrollmentDTO = convertToDTO(enrollment);
            enrollmentDTOList.add(enrollmentDTO);
        }
        return enrollmentDTOList;
    }

    /**
     * Retrieves all enrollments by course ID.
     * 
     * @param courseId The course ID to search enrollments by.
     * @return A list of enrollments by course ID.
     * @throws CourseNotFoundException if course does not exist.
     */

    @Override
    public List<EnrollmentDTO> getAllEnrollmentsByCourseId(Long courseId) {
        if (!courseRepo.existsById(courseId)) {
            throw new CourseNotFoundException("Course not found with this ID.");
        }
        List<Enrollment> enrollmentList = enrollmentRepo.findAllEnrollmentsByCourseId(courseId);
        List<EnrollmentDTO> enrollmentDTOList = new ArrayList<>();
        for (Enrollment enrollment : enrollmentList) {
            EnrollmentDTO enrollmentDTO = convertToDTO(enrollment);
            enrollmentDTOList.add(enrollmentDTO);
        }
        return enrollmentDTOList;
    }
     
   

    /**
     * Retrieves a enrollment by ID.
     *
     * @param enrollmentId The ID of the enrollment to retrieve.
     * @return The retrieved enrollment.
     * @throws EnrollmentNotFoundException if the enrollment is not found.
     */

    @Override
    public EnrollmentDTO getEnrollmentById(Long enrollmentId) {
        Enrollment enrollment = enrollmentRepo.findById(enrollmentId)
            .orElseThrow(()-> new EnrollmentNotFoundException("Enrollment Id not found"));
            return convertToDTO(enrollment);
    }

    /**
     * Retrieves all enrollments by user ID.
     * 
     * @param userId The user ID to search enrollments by.
     * @return A list of enrollments by user ID.
     * @throws UserNotFoundException if user does not exist.
     */

    @Override
    public List<EnrollmentDTO> getAllEnrollmentsByUserId(Long userId) {
        if (!userRepo.existsById(userId)) {
            throw new UserNotFoundException("User not found with this ID.");
        }
        List<Enrollment> enrollmentList = enrollmentRepo.findAllEnrollmentsByUserId(userId);
        List<EnrollmentDTO> enrollmentDTOList = new ArrayList<>();
        for (Enrollment enrollment : enrollmentList) {
            EnrollmentDTO enrollmentDTO = convertToDTO(enrollment);
            enrollmentDTOList.add(enrollmentDTO);
        }
        return enrollmentDTOList;
    }

    /**
     * Deletes an enrollment by ID.
     * 
     * @param enrollmentId The ID of the enrollment to delete.
     * @return The deleted enrollment.
     * @throws EnrollmentNotFoundException if the enrollment is not found.
     */
    @Override
    public EnrollmentDTO deleteEnrollment(Long enrollmentId) {
        Enrollment enrollment = enrollmentRepo.findById(enrollmentId).orElse(null);
        if (enrollment != null) {
            enrollmentRepo.deleteById(enrollmentId);
            return convertToDTO(enrollment);
        }
        throw new EnrollmentNotFoundException("Enrollment not found!");
    }

    /**
     * Converts an EnrollmentDTO to an Enrollment entity.
     * 
     * @param enrollmentDTO The EnrollmentDTO to convert.
     * @param user          The User entity associated with the enrollment.
     * @param course        The Course entity associated with the enrollment.
     * @return The converted Enrollment entity.
     */
    private Enrollment convertToEntity(EnrollmentDTO enrollmentDTO, User user, Course course) {
        return new Enrollment(enrollmentDTO.getEnrollmentId(), user, course, enrollmentDTO.getEnrollmentDate(),
                enrollmentDTO.getStatus());
    }

    /**
     * Converts an Enrollment entity to an EnrollmentDTO.
     * 
     * @param enrollment The Enrollment entity to convert.
     * @return The converted EnrollmentDTO.
     */
    private EnrollmentDTO convertToDTO(Enrollment enrollment) {
        return new EnrollmentDTO(enrollment.getEnrollmentId(), enrollment.getUser().getUserId(),
                enrollment.getCourse().getCourseId(), enrollment.getEnrollmentDate(), enrollment.getStatus());
    }
}