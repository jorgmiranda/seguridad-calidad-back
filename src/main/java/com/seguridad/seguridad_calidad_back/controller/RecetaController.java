package com.seguridad.seguridad_calidad_back.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seguridad.seguridad_calidad_back.dto.Filtro;
import com.seguridad.seguridad_calidad_back.dto.RecetaDTO;
import com.seguridad.seguridad_calidad_back.dto.RecetaParcialDTO;
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

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Receta>> obtenerRecetasByID(@PathVariable Long id) {
        Optional<Receta> receta = recetaService.getRecetaByID(id);
        if (receta.isEmpty()) {
            log.error("No se encontro ninguna Receta con ese ID {} ", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(receta);
        }
        return ResponseEntity.ok(receta);
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

    @PostMapping("/{id}")
    public ResponseEntity<?> editarReceta(@PathVariable Long id, @RequestBody RecetaDTO recetaDTO) {
        try {
            Receta recetaCreada = recetaService.actualizarReceta(id, recetaDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(recetaCreada);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Receta>> obtenerRecetasPorUsuario(@PathVariable Long usuarioId) {
        List<Receta> recetas = recetaService.obtenerRecetasPorUsuario(usuarioId);
        return ResponseEntity.ok(recetas);
    }
     
}
