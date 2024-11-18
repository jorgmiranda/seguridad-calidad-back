package com.seguridad.seguridad_calidad_back.controller;

import com.seguridad.seguridad_calidad_back.model.RecipeCalification;
import com.seguridad.seguridad_calidad_back.model.RecipeComment;
import com.seguridad.seguridad_calidad_back.model.ResponseModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.seguridad.seguridad_calidad_back.dto.Filtro;
import com.seguridad.seguridad_calidad_back.dto.RecetaDTO;
import com.seguridad.seguridad_calidad_back.dto.RecetaParcialDTO;
import com.seguridad.seguridad_calidad_back.model.Receta;
import com.seguridad.seguridad_calidad_back.service.RecetaService;

import java.util.List;
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
    public List<RecetaParcialDTO> getAllRecetasParcial(){
        List<Receta> recetas = recetaService.getAllRecetas();
        List<RecetaParcialDTO> format = new ArrayList<>();
        for(Receta r : recetas){
            RecetaParcialDTO dto = new RecetaParcialDTO();
            dto.setId(r.getId());
            dto.setDificultadElaboracion(r.getDificultadElaboracion());
            dto.setNombre(r.getNombre());
            dto.setTipoDeCocina(r.getTipoDeCocina());
            dto.setTiempoCoccion(r.getTiempoCoccion());
            dto.setUrlImagen(r.getUrlImagen());
            dto.setFechaCreacion(r.getFechaCreacion());
            dto.setPopularidad(r.getPopularidad());

            format.add(dto);
        }

        return format;
    }

    @PostMapping("/filtrar")
    public List<Receta> getFilterRecetas(@RequestBody Filtro filtro){
        List<Receta> recetas = recetaService.filtrarRecetas(filtro.getNombre(), filtro.getPaisOrigen(), filtro.getDificultad(), filtro.getTipoCocina(), filtro.getIngredientes());
        return recetas;
    }

    @PostMapping
    public ResponseEntity<?> crearReceta(@RequestBody RecetaDTO recetaDTO) {
        try {
            Receta recetaCreada = recetaService.crearReceta(recetaDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(recetaCreada);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/comentar")
    public ResponseModel addComment(@RequestBody RecipeComment comment) {
        try {
            return  recetaService.addComment(comment);
        } catch (Exception e){
            return new ResponseModel("Error al guardar comentario", null, e.getMessage());
        }
    }

    @PostMapping("/calificar")
    public ResponseModel addCalification(@RequestBody RecipeCalification calification) {
        try {
            return  recetaService.addCalification(calification);
        }catch (Exception e){
            return new ResponseModel("Error al guardar calificacion", null, e.getMessage());
        }
    }

    @GetMapping("/puntuacion")
    public ResponseModel getCalification(@RequestParam("idRecipe") int idRecipe){
        try{
            return recetaService.getCalification(idRecipe);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
