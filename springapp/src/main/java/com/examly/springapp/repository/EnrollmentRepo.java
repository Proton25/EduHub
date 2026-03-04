package com.examly.springapp.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.examly.springapp.model.Enrollment;
import java.util.Optional;

/**
 * EnrollmentRepo is a repository interface for managing Enrollment entities.
 * It extends JpaRepository to provide CRUD operations and custom query methods.
 **/
@Repository
public interface EnrollmentRepo extends JpaRepository<Enrollment, Long> {

    @Query("SELECT e FROM Enrollment e WHERE e.course.courseId = :courseId")
    List<Enrollment> findAllEnrollmentsByCourseId(Long courseId);

    @Query("SELECT e FROM Enrollment e WHERE e.user.userId = :userId")
    List<Enrollment> findAllEnrollmentsByUserId(Long userId);

    @Query("SELECT e FROM Enrollment e")
    List<Enrollment> findAllEnrollments();

    @Query("SELECT e FROM Enrollment e WHERE e.user.userId = :userId AND e.course.courseId = :courseId")
    Optional<Enrollment> findByUserIdCourseId(Long userId, Long courseId);
}