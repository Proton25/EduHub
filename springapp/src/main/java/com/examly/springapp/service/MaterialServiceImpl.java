package com.examly.springapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springapp.exception.CourseNotFoundException;
import com.examly.springapp.exception.MaterialNotFoundException;
import com.examly.springapp.model.Course;
import com.examly.springapp.model.Material;
import com.examly.springapp.model.dto.MaterialDTO;
import com.examly.springapp.repository.CourseRepo;
import com.examly.springapp.repository.MaterialRepo;

@Service
public class MaterialServiceImpl implements MaterialService {

    @Autowired
    private MaterialRepo materialRepo;

    @Autowired
    private CourseRepo courseRepo;

    @Override
    public MaterialDTO addMaterial(MaterialDTO materialDTO) {
        Course courseObj = courseRepo.findById(materialDTO.getCourseId())
                .orElseThrow(() -> new CourseNotFoundException("Course not found."));
        Material material = toEntity(materialDTO, courseObj);
        Material savedMaterial = materialRepo.save(material);
        return toDTO(savedMaterial);   
    }

    @Override
    public List<MaterialDTO> getAllMaterials() {
        List<Material> materialList = materialRepo.findAllMaterials();
        List<MaterialDTO> materialDTOList = new ArrayList<>();
        for (Material material : materialList) {
            MaterialDTO materialDTO = toDTO(material);
            materialDTOList.add(materialDTO);
        }
        return materialDTOList;
    }

    @Override
    public List<MaterialDTO> getAllMaterialsByCourseId(Long courseId) {
        if (courseRepo.existsById(courseId)) {
            List<Material> materialList = materialRepo.findAllMaterialsByCourseId(courseId);
            List<MaterialDTO> materialDTOList = new ArrayList<>();
            for (Material material : materialList) {
                MaterialDTO materialDTO = toDTO(material);
                materialDTOList.add(materialDTO);
            }
            return materialDTOList;
        }
        throw new MaterialNotFoundException("No Material found for courseId: " + courseId);
    }

    @Override
    public MaterialDTO getMaterialByMaterialId(Long materialId) {
        Material material = materialRepo.findById(materialId)
                .orElseThrow(() -> new MaterialNotFoundException("Material not found with ID: " + materialId));
        return toDTO(material);
    }

    @Override
    public MaterialDTO deleteMaterial(Long materialId) {
        Material found = materialRepo.findById(materialId)
                .orElseThrow(() -> new MaterialNotFoundException("Material not found with the given ID: " + materialId));
        materialRepo.delete(found);
        return toDTO(found);
    }

    // Convert from DTO to Entity
    private Material toEntity(MaterialDTO materialDTO, Course course) {
        return new Material(materialDTO.getMaterialId(), materialDTO.getTitle(), materialDTO.getDescription(),
                materialDTO.getUrl(), materialDTO.getContentType(), course, materialDTO.getUploadDate());
    }

    // Convert from Entity to DTO
    private MaterialDTO toDTO(Material material) {
        return new MaterialDTO(material.getMaterialId(), material.getTitle(), material.getDescription(),
                material.getUrl(), material.getContentType(), material.getCourse().getCourseId(),
                material.getUploadDate());
    }
}
