package com.seguridad.seguridad_calidad_back.service;

import java.util.List;
import com.seguridad.seguridad_calidad_back.model.RecetaIngrediente;

public interface RecetaIngredienteService {
    List<RecetaIngrediente> obtenerPorReceta(Long recetaId);
    List<RecetaIngrediente> obtenerPorIngrediente(Long ingredienteId);
    RecetaIngrediente guardarRecetaIngrediente(RecetaIngrediente recetaIngrediente);
    void eliminarRecetaIngrediente(Long recetaId, Long ingredienteId);
    void actualizarIngredientesDeReceta(Long recetaId, List<Long> nuevosIngredientesIds);
}
