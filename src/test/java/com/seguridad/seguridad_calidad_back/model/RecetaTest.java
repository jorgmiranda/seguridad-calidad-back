package com.seguridad.seguridad_calidad_back.model;

import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class RecetaTest {

    @Test
    void testGettersAndSetters() {
        // Arrange
        Receta receta = new Receta();
        Long id = 1L;
        String nombre = "Tarta de Manzana";
        String tipoDeCocina = "Postre";
        String paisDeOrigen = "España";
        String dificultadElaboracion = "Fácil";
        String instruccionesPreparacion = "Mezclar y hornear";
        Integer tiempoCoccion = 45;
        String urlImagen = "https://example.com/tarta.jpg";
        Date fechaCreacion = new Date();
        Integer popularidad = 5;
        Set<RecetaIngrediente> recetaIngredientes = new HashSet<>();
        UserModel usuario = new UserModel();
        String urlVideo = "https://example.com/video.mp4";

        // Act
        receta.setId(id);
        receta.setNombre(nombre);
        receta.setTipoDeCocina(tipoDeCocina);
        receta.setPaisDeOrigen(paisDeOrigen);
        receta.setDificultadElaboracion(dificultadElaboracion);
        receta.setInstruccionesPreparacion(instruccionesPreparacion);
        receta.setTiempoCoccion(tiempoCoccion);
        receta.setUrlImagen(urlImagen);
        receta.setFechaCreacion(fechaCreacion);
        receta.setPopularidad(popularidad);
        receta.setRecetaIngredientes(recetaIngredientes);
        receta.setUsuario(usuario);
        receta.setUrlVideo(urlVideo);

        // Assert
        assertEquals(id, receta.getId());
        assertEquals(nombre, receta.getNombre());
        assertEquals(tipoDeCocina, receta.getTipoDeCocina());
        assertEquals(paisDeOrigen, receta.getPaisDeOrigen());
        assertEquals(dificultadElaboracion, receta.getDificultadElaboracion());
        assertEquals(instruccionesPreparacion, receta.getInstruccionesPreparacion());
        assertEquals(tiempoCoccion, receta.getTiempoCoccion());
        assertEquals(urlImagen, receta.getUrlImagen());
        assertEquals(fechaCreacion, receta.getFechaCreacion());
        assertEquals(popularidad, receta.getPopularidad());
        assertEquals(recetaIngredientes, receta.getRecetaIngredientes());
        assertEquals(usuario, receta.getUsuario());
        assertEquals(urlVideo, receta.getUrlVideo());
    }

    @Test
    void testConstructorWithParameters() {
        // Arrange
        Long id = 2L;
        String nombre = "Paella";
        String tipoDeCocina = "Plato Principal";
        String paisDeOrigen = "España";
        String dificultadElaboracion = "Media";
        String instruccionesPreparacion = "Cocinar arroz con ingredientes";
        Integer tiempoCoccion = 60;
        String urlImagen = "https://example.com/paella.jpg";
        Date fechaCreacion = new Date();
        Integer popularidad = 8;
        Set<RecetaIngrediente> recetaIngredientes = new HashSet<>();
        Receta receta = new Receta(id, nombre, tipoDeCocina, paisDeOrigen, dificultadElaboracion,
                instruccionesPreparacion, tiempoCoccion, urlImagen, fechaCreacion, popularidad, recetaIngredientes);

        // Assert
        assertEquals(id, receta.getId());
        assertEquals(nombre, receta.getNombre());
        assertEquals(tipoDeCocina, receta.getTipoDeCocina());
        assertEquals(paisDeOrigen, receta.getPaisDeOrigen());
        assertEquals(dificultadElaboracion, receta.getDificultadElaboracion());
        assertEquals(instruccionesPreparacion, receta.getInstruccionesPreparacion());
        assertEquals(tiempoCoccion, receta.getTiempoCoccion());
        assertEquals(urlImagen, receta.getUrlImagen());
        assertEquals(fechaCreacion, receta.getFechaCreacion());
        assertEquals(popularidad, receta.getPopularidad());
        assertEquals(recetaIngredientes, receta.getRecetaIngredientes());
    }

    @Test
    void testDefaultConstructor() {
        // Act
        Receta receta = new Receta();

        // Assert
        assertNotNull(receta);
        assertNull(receta.getId());
        assertNull(receta.getNombre());
        assertNull(receta.getTipoDeCocina());
        assertNull(receta.getPaisDeOrigen());
        assertNull(receta.getDificultadElaboracion());
        assertNull(receta.getInstruccionesPreparacion());
        assertNull(receta.getTiempoCoccion());
        assertNull(receta.getUrlImagen());
        assertNull(receta.getFechaCreacion());
        assertNull(receta.getPopularidad());
        assertNull(receta.getRecetaIngredientes());
        assertNull(receta.getUsuario());
        assertNull(receta.getUrlVideo());
    }
}
