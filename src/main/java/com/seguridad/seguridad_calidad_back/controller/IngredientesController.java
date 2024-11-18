package com.seguridad.seguridad_calidad_back.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seguridad.seguridad_calidad_back.dto.IngredientesDTO;
import com.seguridad.seguridad_calidad_back.model.Ingrediente;
import com.seguridad.seguridad_calidad_back.service.IngredienteService;

@RestController
@RequestMapping("/ingredientes")
public class IngredientesController {
    @Autowired
    private IngredienteService ingredienteService;

    @GetMapping
    public List<IngredientesDTO> getAllIngredientes(){
        List<IngredientesDTO> ingredientes = new ArrayList<>(); 
        for(Ingrediente in : ingredienteService.getAllIngredientes()){
            IngredientesDTO dto = new IngredientesDTO();
            dto.setId(in.getId());
            dto.setNombreIngrediente(in.getNombre());
            ingredientes.add(dto);
        }

        return ingredientes;
    }
}
