package com.seguridad.seguridad_calidad_back.service;

import java.util.List;
import java.util.Optional;

import com.seguridad.seguridad_calidad_back.dto.RecetaDTO;
import com.seguridad.seguridad_calidad_back.model.Receta;

public interface RecetaService {
    List<Receta> getAllRecetas();
    Optional<Receta> getRecetaByID(Long id);
    Receta crearReceta (RecetaDTO recetaDTO);
    Receta actualizarReceta(Long id, RecetaDTO recetaDTO);
    void eliminarReceta(Long id);
    List<Receta> filtrarRecetas(String nombre, String pais, String dificultad, String tipo, String ingrediente);
    List<Receta> obtenerRecetasPorUsuario(Long usuarioId);
}
