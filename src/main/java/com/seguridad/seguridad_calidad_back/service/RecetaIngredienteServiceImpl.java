package com.seguridad.seguridad_calidad_back.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.seguridad.seguridad_calidad_back.model.RecetaIngrediente;
import com.seguridad.seguridad_calidad_back.model.RecetaIngredienteId;
import com.seguridad.seguridad_calidad_back.repository.IngredienteRepository;
import com.seguridad.seguridad_calidad_back.repository.RecetaIngredienteRepository;
import com.seguridad.seguridad_calidad_back.repository.RecetaRepository;

public class RecetaIngredienteServiceImpl implements RecetaIngredienteService{
    @Autowired
    private RecetaIngredienteRepository recetaIngredienteRepository;

    @Autowired
    private RecetaRepository recetaRepository;

    @Autowired
    private IngredienteRepository ingredienteRepository;

    // @Override
    // public List<RecetaIngrediente> obtenerPorReceta(Long recetaId) {
    //     return recetaIngredienteRepository.findByIdRecetaId(recetaId);
    // }

    // @Override
    // public List<RecetaIngrediente> obtenerPorIngrediente(Long ingredienteId) {
    //     return recetaIngredienteRepository.findByIdIngredienteId(ingredienteId);
    // }

    @Override
    public RecetaIngrediente guardarRecetaIngrediente(RecetaIngrediente recetaIngrediente) {
        return recetaIngredienteRepository.save(recetaIngrediente);
    }

    @Override
    public void eliminarRecetaIngrediente(Long recetaId, Long ingredienteId) {
        RecetaIngredienteId id = new RecetaIngredienteId(recetaId, ingredienteId);
        recetaIngredienteRepository.deleteById(id);
    }

    // @Override
    // public void actualizarIngredientesDeReceta(Long recetaId, List<Long> nuevosIngredientesIds) {
    //      // Paso 1: Eliminar los ingredientes existentes para la receta
    //      List<RecetaIngrediente> existentes = recetaIngredienteRepository.findByIdRecetaId(recetaId);
    //      recetaIngredienteRepository.deleteAll(existentes);
 
    //      // Paso 2: Agregar los nuevos ingredientes
    //      List<RecetaIngrediente> nuevosIngredientes = nuevosIngredientesIds.stream()
    //              .map(ingredienteId -> {
    //                  RecetaIngrediente recetaIngrediente = new RecetaIngrediente();
    //                  recetaIngrediente.setReceta(recetaRepository.findById(recetaId).orElseThrow(() -> new RuntimeException("Receta no encontrada")));
    //                  recetaIngrediente.setIngrediente(ingredienteRepository.findById(ingredienteId).orElseThrow(() -> new RuntimeException("Ingrediente no encontrado")));
    //                  return recetaIngrediente;
    //              })
    //              .collect(Collectors.toList());
 
    //      recetaIngredienteRepository.saveAll(nuevosIngredientes);
    //  }
    

    
    
}
