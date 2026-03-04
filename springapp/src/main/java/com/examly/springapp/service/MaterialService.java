package com.examly.springapp.service;

import java.util.List;
import com.examly.springapp.model.dto.MaterialDTO;

//MaterialService interface defindes the method for material-related services
public interface MaterialService {

    // Adds a new feedback
    MaterialDTO addMaterial(MaterialDTO material);

    // Retrieves all materials
    List<MaterialDTO> getAllMaterials();

    // Retrieves materials for a specific course based on courseId
    List<MaterialDTO> getAllMaterialsByCourseId(Long courseId);

    // Retrieving materials by materialId
    MaterialDTO getMaterialByMaterialId(Long materialId);

    // Deletes a specific material based on materialId
    MaterialDTO deleteMaterial(Long materialId);

}