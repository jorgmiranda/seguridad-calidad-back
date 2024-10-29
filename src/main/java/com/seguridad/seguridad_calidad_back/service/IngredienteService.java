package com.seguridad.seguridad_calidad_back.service;

import java.util.List;
import java.util.Optional;

import com.seguridad.seguridad_calidad_back.model.Ingrediente;

public interface IngredienteService {
    List<Ingrediente> getAllIngredientes();
    Optional<Ingrediente> getIngredienteByID(Long id);
    Ingrediente crearIngrediente (Ingrediente ingrediente);
    Ingrediente actualizarIngrediente(Long id, Ingrediente ingrediente);
    void eliminarIngrediente(Long id);
}
