package com.seguridad.seguridad_calidad_back.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seguridad.seguridad_calidad_back.model.Ingrediente;

public interface IngredienteRepository extends JpaRepository<Ingrediente, Long>{
    
}
