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

import com.examly.springapp.model.dto.MaterialDTO;
import com.examly.springapp.service.MaterialServiceImpl;

import jakarta.validation.Valid;

@RestController
public class MaterialController {
    @Autowired

    private MaterialServiceImpl materialServiceImpl;

    /**
     * Add a new material.
     * 
     * @param material The material to add.
     * @return ResponseEntity with the created material and HTTP status 201.
     */

    @PostMapping("api/material")
    // @PreAuthorize("hasRole('EDUCATOR')")
    public ResponseEntity<MaterialDTO> addMaterial(@Valid @RequestBody MaterialDTO material) {
        MaterialDTO createdMaterial = materialServiceImpl.addMaterial(material);
        if (createdMaterial == null) {
            return ResponseEntity.status(500).build();
        }
        return ResponseEntity.status(201).body(createdMaterial);
    }

    /**
     * Retrieve all materials.
     * 
     * @return ResponseEntity with a list of all material and HTTP status 200.
     */

    @GetMapping("api/material")
    // @PreAuthorize("hasAnyRole('EDUCATOR','STUDENT')")
    public ResponseEntity<List<MaterialDTO>> getAllMaterials() {
        List<MaterialDTO> list = materialServiceImpl.getAllMaterials();
        if (list != null) {
            return ResponseEntity.status(200).body(list);
        }
        return ResponseEntity.status(500).build();
    }

    /**
     * Delete a material by ID.
     * 
     * @param materialId The ID of the material to delete.
     * @return ResponseEntity with the deleted material and HTTP status 200.
     */

    @DeleteMapping("api/material/{materialId}")
    // @PreAuthorize("hasRole('EDUCATOR')")
    public ResponseEntity<MaterialDTO> deleteMaterial(@PathVariable Long materialId) {
        MaterialDTO material = materialServiceImpl.deleteMaterial(materialId);
        if (material != null) {
            return ResponseEntity.status(200).body(material);
        }
        return ResponseEntity.status(404).build();
    }

    /**
     * Retrieve all materials for a specific course by its Id.
     * 
     * @param courseId The ID of the course to retrieve materials for.
     * @return ResponseEntity containing a list of materials and HTTP status 200.
     */

    @GetMapping("api/material/course/{courseId}")
    // @PreAuthorize("hasAnyRole('EDUCATOR','STUDENT')")
    public ResponseEntity<List<MaterialDTO>> getAllMaterialsByCourseId(@PathVariable Long courseId) {
        List<MaterialDTO> material = materialServiceImpl.getAllMaterialsByCourseId(courseId);
        if (material != null) {
            return ResponseEntity.status(200).body(material);
        }
        return ResponseEntity.status(500).build();
    }

    /**
     * Retrieve a material by ID.
     * 
     * @param materialId The ID of the material to retrieve.
     * @return ResponseEntity with the material course and HTTP status 200.
     */

    @GetMapping("/api/material/{materialId}")   
    // @PreAuthorize("hasAnyRole('EDUCATOR','STUDENT')") 
    public ResponseEntity<MaterialDTO> getMaterialByMaterialId(@PathVariable Long materialId) {
        MaterialDTO material = materialServiceImpl.getMaterialByMaterialId(materialId);
        if (material != null) {
            return ResponseEntity.status(200).body(material);
        }
        return ResponseEntity.status(404).build();
    }

}