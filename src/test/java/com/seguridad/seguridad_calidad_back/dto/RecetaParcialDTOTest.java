package com.seguridad.seguridad_calidad_back.dto;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class RecetaParcialDTOTest {

    @Test
    void testDefaultConstructor() {
        RecetaParcialDTO recetaParcialDTO = new RecetaParcialDTO();

        assertNull(recetaParcialDTO.getId());
        assertNull(recetaParcialDTO.getNombre());
        assertNull(recetaParcialDTO.getTipoDeCocina());
        assertNull(recetaParcialDTO.getDificultadElaboracion());
        assertEquals(0, recetaParcialDTO.getTiempoCoccion());
        assertNull(recetaParcialDTO.getUrlImagen());
        assertNull(recetaParcialDTO.getPopularidad());
        assertNull(recetaParcialDTO.getFechaCreacion());
    }

    @Test
    void testParameterizedConstructor() {
        Long id = 1L;
        String nombre = "Tacos";
        String tipoDeCocina = "Mexicana";
        String dificultadElaboracion = "Fácil";
        int tiempoCoccion = 15;
        String urlImagen = "http://example.com/tacos.jpg";

        RecetaParcialDTO recetaParcialDTO = new RecetaParcialDTO(id, nombre, tipoDeCocina, dificultadElaboracion, tiempoCoccion, urlImagen);

        assertEquals(id, recetaParcialDTO.getId());
        assertEquals(nombre, recetaParcialDTO.getNombre());
        assertEquals(tipoDeCocina, recetaParcialDTO.getTipoDeCocina());
        assertEquals(dificultadElaboracion, recetaParcialDTO.getDificultadElaboracion());
        assertEquals(tiempoCoccion, recetaParcialDTO.getTiempoCoccion());
        assertEquals(urlImagen, recetaParcialDTO.getUrlImagen());
    }

    @Test
    void testSettersAndGetters() {
        RecetaParcialDTO recetaParcialDTO = new RecetaParcialDTO();

        Long id = 1L;
        String nombre = "Tacos";
        String tipoDeCocina = "Mexicana";
        String dificultadElaboracion = "Fácil";
        int tiempoCoccion = 15;
        String urlImagen = "http://example.com/tacos.jpg";
        Integer popularidad = 5;
        Date fechaCreacion = new Date();

        recetaParcialDTO.setId(id);
        recetaParcialDTO.setNombre(nombre);
        recetaParcialDTO.setTipoDeCocina(tipoDeCocina);
        recetaParcialDTO.setDificultadElaboracion(dificultadElaboracion);
        recetaParcialDTO.setTiempoCoccion(tiempoCoccion);
        recetaParcialDTO.setUrlImagen(urlImagen);
        recetaParcialDTO.setPopularidad(popularidad);
        recetaParcialDTO.setFechaCreacion(fechaCreacion);

        assertEquals(id, recetaParcialDTO.getId());
        assertEquals(nombre, recetaParcialDTO.getNombre());
        assertEquals(tipoDeCocina, recetaParcialDTO.getTipoDeCocina());
        assertEquals(dificultadElaboracion, recetaParcialDTO.getDificultadElaboracion());
        assertEquals(tiempoCoccion, recetaParcialDTO.getTiempoCoccion());
        assertEquals(urlImagen, recetaParcialDTO.getUrlImagen());
        assertEquals(popularidad, recetaParcialDTO.getPopularidad());
        assertEquals(fechaCreacion, recetaParcialDTO.getFechaCreacion());
    }
}
