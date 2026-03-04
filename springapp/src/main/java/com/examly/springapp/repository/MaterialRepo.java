package com.examly.springapp.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.examly.springapp.model.Material;

/**
 * Repository interface for managing Material entities.
 * This interface extends JpaRepository, providing CRUD operations and
 * custom query methods for Material entities.
 */
@Repository
public interface MaterialRepo extends JpaRepository<Material, Long> {

    @Query("SELECT m FROM Material m WHERE m.title = :title")
    Optional<Material> findByTitle(String title);

    @Query("SELECT m FROM Material m WHERE m.course.courseId = :courseId")
    List<Material> findAllMaterialsByCourseId(Long courseId);

    @Query("SELECT m FROM Material m")
    List<Material> findAllMaterials();
}