package com.examly.springapp.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.examly.springapp.model.Course;

/**
 * Repository interface for managing Course entities.
 * This interface extends JpaRepository, providing CRUD operations and
 * custom query methods for Course entities.
 */
@Repository
public interface CourseRepo extends JpaRepository<Course,Long> {

    @Query("SELECT c FROM Course c WHERE c.title = :title")
    Optional<Course> findByTitle(String title);

    @Query("SELECT c FROM Course c")
    List<Course> findAllCourses();

}

