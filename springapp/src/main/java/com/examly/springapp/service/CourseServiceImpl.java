package com.examly.springapp.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springapp.exception.CourseAlreadyExistsException;
import com.examly.springapp.exception.CourseNotFoundException;
import com.examly.springapp.model.Course;
import com.examly.springapp.model.dto.CourseDTO;
import com.examly.springapp.repository.CourseRepo;

/**
 * CourseServiceImpl provides implementations for the CourseService interface.
 * It manages the business logic for adding, editing, retrieving, and deleting
 * courses.
 * This service relies on CourseRepo for database interactions and throws custom
 * exceptions
 * for handling course-related errors.
 */

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepo courseRepo;

    /**
     * Adds a new course if it does not already exist.
     * 
     * @param courseDTO The course to add.
     * @return The added course.
     * @throws CourseAlreadyExistsException if the course already exists.
     */
    @Override
    public CourseDTO addCourse(CourseDTO courseDTO) {
        Course courseObj = courseRepo.findByTitle(courseDTO.getTitle()).orElse(null);
        if(courseObj == null){
        Course course = convertToEntity(courseDTO);
        Course savedCourse = courseRepo.save(course);
        return convertToDTO(savedCourse);
        }
        throw new CourseAlreadyExistsException("Course with title " + courseDTO.getTitle() + " already exists.");
    }

    /**
     * Retrieves all courses.
     * 
     * @return A list of all courses.
     */
    @Override
    public List<CourseDTO> getAllCourses() {
        List<Course> courseList = courseRepo.findAllCourses();
        List<CourseDTO> courseDTOList = new ArrayList<>();
        for (Course course : courseList) {
            CourseDTO courseDTO = convertToDTO(course);
            courseDTOList.add(courseDTO);
        }
        return courseDTOList;
    }

    /**
     * 
     * Edits an existing course by ID.
     * 
     * @param courseId  The ID of the course to edit.
     * @param courseDTO The updated course details.
     * @return The edited course.
     * @throws CourseNotFoundException if the course is not found.
     */
    @Override
    public CourseDTO editCourse(Long courseId, CourseDTO courseDTO) {
        Course course = courseRepo.findById(courseId)
           .orElseThrow(() -> new CourseNotFoundException("Course with courseId: " + courseId + " not Found."));
        course.setTitle(courseDTO.getTitle());
        course.setDescription(courseDTO.getDescription());
        course.setCourseStartDate(courseDTO.getCourseStartDate());
        course.setCourseEndDate(courseDTO.getCourseEndDate());
        course.setCategory(courseDTO.getCategory());
        course.setLevel(courseDTO.getLevel());
        Course updatedCourse = courseRepo.save(course);
        return convertToDTO(updatedCourse);
    }

    /**
     * Deletes a course by ID.
     * 
     * @param courseId The ID of the course to delete.
     * @return The deleted course.
     * @throws CourseNotFoundException if the course is not found.
     */
    @Override
    public CourseDTO deleteCourse(Long courseId) {
        Course course = courseRepo.findById(courseId)
            .orElseThrow(() -> new CourseNotFoundException("Course with courseId " + courseId + " not found."));
            courseRepo.deleteById(courseId);
            return convertToDTO(course);
    }

    /**
     * Retrieves a course by ID.
     * 
     * @param courseId The ID of the course to retrieve.
     * @return The retrieved course.
     * @throws CourseNotFoundException if the course is not found.
     */
    @Override
    public CourseDTO getCourseById(Long courseId) {
        Course course = courseRepo.findById(courseId)
             .orElseThrow(() -> new CourseNotFoundException("Course with courseId " + courseId + " not found."));
        return convertToDTO(course);
       
    }

    /**
     * Converts a CourseDTO to a Course entity.
     * 
     * @param courseDTO The CourseDTO to convert.
     * @return The converted Course entity.
     */
    private Course convertToEntity(CourseDTO courseDTO) {
        return new Course(courseDTO.getCourseId(), courseDTO.getTitle(), courseDTO.getDescription(),
                courseDTO.getCourseStartDate(), courseDTO.getCourseEndDate(), courseDTO.getCategory(),
                courseDTO.getLevel());
    }

    /**
     * Converts a Course entity to a CourseDTO.
     * 
     * @param course The Course entity to convert.
     * @return The converted CourseDTO.
     */
    private CourseDTO convertToDTO(Course course) {
        return new CourseDTO(course.getCourseId(), course.getTitle(), course.getDescription(),
                course.getCourseStartDate(), course.getCourseEndDate(), course.getCategory(),
                course.getLevel());
    }
}
