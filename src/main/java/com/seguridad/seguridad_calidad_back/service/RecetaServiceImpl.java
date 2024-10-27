package com.seguridad.seguridad_calidad_back.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seguridad.seguridad_calidad_back.model.Receta;
import com.seguridad.seguridad_calidad_back.repository.RecetaRepository;

@Service
public class RecetaServiceImpl implements RecetaService{
    @Autowired
    private RecetaRepository recetaRepository;

    @Override
    public List<Receta> getAllRecetas() {
        return recetaRepository.findAll();
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
        }else{
            return null;
        }
    }

    @Override
    public void eliminarReceta(Long id) {
        recetaRepository.deleteById(id);
    }

    
}
