package com.seguridad.seguridad_calidad_back.dto;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RecetaDTOTest {

    @Test
    void testDefaultConstructor() {
        RecetaDTO recetaDTO = new RecetaDTO();

        assertNull(recetaDTO.getNombre());
        assertNull(recetaDTO.getTipoDeCocina());
        assertNull(recetaDTO.getPaisDeOrigen());
        assertNull(recetaDTO.getDificultadElaboracion());
        assertNull(recetaDTO.getInstruccionesPreparacion());
        assertNull(recetaDTO.getTiempoCoccion());
        assertNull(recetaDTO.getUrlImagen());
        assertNull(recetaDTO.getUrlVideo());
        assertNull(recetaDTO.getPopularidad());
        assertNull(recetaDTO.getIngredientes());
        assertNull(recetaDTO.getUsuarioId());
    }

    @Test
    void testSettersAndGetters() {
        RecetaDTO recetaDTO = new RecetaDTO();

        String nombre = "Paella";
        String tipoDeCocina = "Española";
        String paisDeOrigen = "España";
        String dificultadElaboracion = "Intermedia";
        String instruccionesPreparacion = "Paso 1: Preparar ingredientes.";
        Integer tiempoCoccion = 45;
        String urlImagen = "http://example.com/paella.jpg";
        String urlVideo = "http://example.com/paella-video.mp4";
        Integer popularidad = 10;
        List<String> ingredientes = Arrays.asList("Arroz", "Mariscos", "Azafrán");
        Long usuarioId = 1L;

        recetaDTO.setNombre(nombre);
        recetaDTO.setTipoDeCocina(tipoDeCocina);
        recetaDTO.setPaisDeOrigen(paisDeOrigen);
        recetaDTO.setDificultadElaboracion(dificultadElaboracion);
        recetaDTO.setInstruccionesPreparacion(instruccionesPreparacion);
        recetaDTO.setTiempoCoccion(tiempoCoccion);
        recetaDTO.setUrlImagen(urlImagen);
        recetaDTO.setUrlVideo(urlVideo);
        recetaDTO.setPopularidad(popularidad);
        recetaDTO.setIngredientes(ingredientes);
        recetaDTO.setUsuarioId(usuarioId);

        assertEquals(nombre, recetaDTO.getNombre());
        assertEquals(tipoDeCocina, recetaDTO.getTipoDeCocina());
        assertEquals(paisDeOrigen, recetaDTO.getPaisDeOrigen());
        assertEquals(dificultadElaboracion, recetaDTO.getDificultadElaboracion());
        assertEquals(instruccionesPreparacion, recetaDTO.getInstruccionesPreparacion());
        assertEquals(tiempoCoccion, recetaDTO.getTiempoCoccion());
        assertEquals(urlImagen, recetaDTO.getUrlImagen());
        assertEquals(urlVideo, recetaDTO.getUrlVideo());
        assertEquals(popularidad, recetaDTO.getPopularidad());
        assertEquals(ingredientes, recetaDTO.getIngredientes());
        assertEquals(usuarioId, recetaDTO.getUsuarioId());
    }

    @Test
    void testEquality() {
        RecetaDTO receta1 = new RecetaDTO();
        RecetaDTO receta2 = new RecetaDTO();

        receta1.setNombre("Paella");
        receta1.setTipoDeCocina("Española");

        receta2.setNombre("Paella");
        receta2.setTipoDeCocina("Española");

        assertEquals(receta1.getNombre(), receta2.getNombre());
        assertEquals(receta1.getTipoDeCocina(), receta2.getTipoDeCocina());
    }

    @Test
    void testNullValues() {
        RecetaDTO recetaDTO = new RecetaDTO();

        recetaDTO.setNombre(null);
        recetaDTO.setTipoDeCocina(null);
        recetaDTO.setIngredientes(null);

        assertNull(recetaDTO.getNombre());
        assertNull(recetaDTO.getTipoDeCocina());
        assertNull(recetaDTO.getIngredientes());
    }
}
