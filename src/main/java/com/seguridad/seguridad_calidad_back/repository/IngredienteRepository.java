package com.seguridad.seguridad_calidad_back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seguridad.seguridad_calidad_back.model.Ingrediente;

public interface IngredienteRepository extends JpaRepository<Ingrediente, Long>{
    List<Ingrediente> findByNombreIn(List<String> nombres);
}
