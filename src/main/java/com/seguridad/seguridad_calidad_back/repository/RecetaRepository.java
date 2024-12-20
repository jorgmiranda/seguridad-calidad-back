package com.seguridad.seguridad_calidad_back.repository;

import com.seguridad.seguridad_calidad_back.model.Receta;

import java.util.List;

import com.seguridad.seguridad_calidad_back.model.RecipeComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface RecetaRepository  extends JpaRepository<Receta, Long>, JpaSpecificationExecutor<Receta>{
    List<Receta> findByUsuarioId(Long usuarioId);
}
