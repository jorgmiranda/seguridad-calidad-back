package com.seguridad.seguridad_calidad_back.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seguridad.seguridad_calidad_back.dto.RecetaDTO;
import com.seguridad.seguridad_calidad_back.model.Receta;
import com.seguridad.seguridad_calidad_back.service.RecetaService;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

@RestController
@RequestMapping("/recetas")
public class RecetaController {
    private static final Logger log = LoggerFactory.getLogger(RecetaController.class);

    @Autowired
    private RecetaService recetaService;

    @GetMapping("/full")
    public List<Receta> getAllRecetas(){
        return recetaService.getAllRecetas();
    }

    @GetMapping("/parcial")
    public List<RecetaDTO> getAllRecetasParcial(){
        List<Receta> recetas = recetaService.getAllRecetas();
        List<RecetaDTO> format = new ArrayList<>();
        for(Receta r : recetas){
            RecetaDTO dto = new RecetaDTO();
            dto.setId(r.getId());
            dto.setDificultadElaboracion(r.getDificultadElaboracion());
            dto.setNombre(r.getNombre());
            dto.setTipoDeCocina(r.getTipoDeCocina());
            dto.setTiempoCoccion(r.getTiempoCoccion());

            format.add(dto);
        }

        return format;
    }
     
}
