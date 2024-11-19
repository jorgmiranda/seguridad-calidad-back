package com.seguridad.seguridad_calidad_back.repository;

import com.seguridad.seguridad_calidad_back.model.RecipeCalification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeCalificationRepository extends JpaRepository<RecipeCalification, Long> {
    List<RecipeCalification> getAllByIdReceta(int idReceta);
}
