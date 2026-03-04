package com.examly.springapp.service;

import java.util.List;
import com.examly.springapp.model.Course;
import com.examly.springapp.model.dto.CourseDTO;
 
/**
 * Service interface for managing courses.
 * This interface defines the operations that can be performed
 * on the course entities, including adding, retrieving, updating,
 * and deleting courses. Implementations of this interface should
 * provide the actual business logic for these operations.
 */
public interface CourseService {

    /**
     * Adds a new course.
     * 
     * @param courseDTO The course to add.
     * @return The added course.
     */
    CourseDTO addCourse(CourseDTO course);

    /**
     * Retrieves all courses.
     * 
     * @return A list of all courses.
     */
    List<CourseDTO> getAllCourses();

    /**
     * Edits a specific course based on course ID.
     * 
     * @param courseId The ID of the course to edit.
     * @param updatedCourseDTO The updated course details.
     * @return The edited course.
     */
    CourseDTO editCourse(Long courseId, CourseDTO updatedCourse);

    /**
     * Deletes a specific course based on course ID.
     * 
     * @param courseId The ID of the course to delete.
     * @return The deleted course.
     */
    CourseDTO deleteCourse(Long courseId);

    /**
     * Retrieves a course by its ID.
     * 
     * @param courseId The ID of the course to retrieve.
     * @return The retrieved course.
     */
    CourseDTO getCourseById(Long courseId);
}