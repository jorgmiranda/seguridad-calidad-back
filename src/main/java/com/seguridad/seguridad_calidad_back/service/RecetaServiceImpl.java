package com.seguridad.seguridad_calidad_back.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.seguridad.seguridad_calidad_back.model.Receta;
import com.seguridad.seguridad_calidad_back.repository.RecetaRepository;
import com.seguridad.seguridad_calidad_back.specifications.RecetaSpecifications;

@Service
public class RecetaServiceImpl implements RecetaService {
    @Autowired
    private RecetaRepository recetaRepository;

    @Override
    public List<Receta> getAllRecetas() {
        return recetaRepository.findAll();
    }

    public List<Receta> filtrarRecetas(String nombre, String pais, String dificultad, String tipo, String ingrediente) {
        Specification<Receta> spec = Specification.where(null); // Inicializa como null

        if (nombre != null && !nombre.trim().isEmpty()) {
           
            spec = spec.and(RecetaSpecifications.nombreContains(nombre));
        }
        if (pais != null && !pais.trim().isEmpty()) {
            
            spec = spec.and(RecetaSpecifications.paisOrigenEquals(pais));
        }
        if (dificultad != null && !dificultad.trim().isEmpty()) {
           
            spec = spec.and(RecetaSpecifications.dificultadEquals(dificultad));
        }
        if (tipo != null && !tipo.trim().isEmpty()) {
           
            spec = spec.and(RecetaSpecifications.tipoEquals(tipo));
        }

        if (ingrediente != null && !ingrediente.isEmpty()) {
            
            spec = spec.and(RecetaSpecifications.ingredienteContains(ingrediente));
        }

        return recetaRepository.findAll(spec);
    }

    @Override
    public Optional<Receta> getRecetaByID(Long id) {
        return recetaRepository.findById(id);
    }

    @Override
    public Receta crearReceta(Receta receta) {
        return recetaRepository.save(receta);
    }

    @Override
    public Receta actualizarReceta(Long id, Receta receta) {
        if (recetaRepository.existsById(id)) {
            receta.setId(id);
            return recetaRepository.save(receta);
        } else {
            return null;
        }
    }

    @Override
    public void eliminarReceta(Long id) {
        recetaRepository.deleteById(id);
    }

}
