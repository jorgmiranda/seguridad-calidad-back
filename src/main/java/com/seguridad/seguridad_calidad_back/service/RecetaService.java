package com.seguridad.seguridad_calidad_back.service;

import java.util.List;
import java.util.Optional;

import com.seguridad.seguridad_calidad_back.model.Receta;

public interface RecetaService {
    List<Receta> getAllRecetas();
    Optional<Receta> getRecetaByID(Long id);
    Receta crearReceta (Receta receta);
    Receta actualizarReceta(Long id, Receta receta);
    void eliminarReceta(Long id);
}
