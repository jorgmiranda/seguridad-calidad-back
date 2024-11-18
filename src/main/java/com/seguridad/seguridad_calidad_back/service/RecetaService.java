package com.seguridad.seguridad_calidad_back.service;

import java.util.List;
import java.util.Optional;

import com.seguridad.seguridad_calidad_back.dto.RecetaDTO;
import com.seguridad.seguridad_calidad_back.model.Receta;
import com.seguridad.seguridad_calidad_back.model.RecipeCalification;
import com.seguridad.seguridad_calidad_back.model.RecipeComment;
import com.seguridad.seguridad_calidad_back.model.ResponseModel;

public interface RecetaService {
    List<Receta> getAllRecetas();
    Optional<Receta> getRecetaByID(Long id);
    Receta crearReceta (RecetaDTO recetaDTO);
    Receta actualizarReceta(Long id, Receta receta);
    void eliminarReceta(Long id);
    List<Receta> filtrarRecetas(String nombre, String pais, String dificultad, String tipo, String ingrediente);

    ResponseModel addComment(RecipeComment comment);

    ResponseModel addCalification(RecipeCalification calification);

    ResponseModel getCalification(int id);

}
