package com.seguridad.seguridad_calidad_back.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import com.seguridad.seguridad_calidad_back.model.RecetaIngrediente;
import com.seguridad.seguridad_calidad_back.model.RecetaIngredienteId;

public interface RecetaIngredienteRepository extends JpaRepository<RecetaIngrediente, RecetaIngredienteId> {
    // Buscar por recetaId dentro de la clave compuesta
    List<RecetaIngrediente> findByIdRecetaId(Long recetaId);

    // Buscar por ingredienteId dentro de la clave compuesta
    List<RecetaIngrediente> findByIdIngredienteId(Long ingredienteId);
}

