package com.seguridad.seguridad_calidad_back.service.serviceImpl;

import java.util.List;
import java.util.Optional;

import com.seguridad.seguridad_calidad_back.service.IngredienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seguridad.seguridad_calidad_back.model.Ingrediente;
import com.seguridad.seguridad_calidad_back.repository.IngredienteRepository;

@Service
public class IngredienteServiceImpl implements IngredienteService {
    @Autowired
    private IngredienteRepository ingredienteRepository;

    @Override
    public List<Ingrediente> getAllIngredientes() {
        return ingredienteRepository.findAll();
    }

    @Override
    public Optional<Ingrediente> getIngredienteByID(Long id) {
        return ingredienteRepository.findById(id);
    }

    @Override
    public Ingrediente crearIngrediente(Ingrediente ingrediente) {
        return ingredienteRepository.save(ingrediente);
    }

    @Override
    public Ingrediente actualizarIngrediente(Long id, Ingrediente ingrediente) {
        if (ingredienteRepository.existsById(id)) {
            ingrediente.setId(id);
            return ingredienteRepository.save(ingrediente);
        }else{
            return null;
        }
    }

    @Override
    public void eliminarIngrediente(Long id) {
        ingredienteRepository.deleteById(id);
    }

    
}
