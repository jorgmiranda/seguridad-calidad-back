package com.seguridad.seguridad_calidad_back.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FiltroTest {

    @Test
    void testDefaultConstructor() {
        Filtro filtro = new Filtro();

        assertNull(filtro.getNombre());
        assertNull(filtro.getTipoCocina());
        assertNull(filtro.getIngredientes());
        assertNull(filtro.getPaisOrigen());
        assertNull(filtro.getDificultad());
    }

    @Test
    void testParameterizedConstructor() {
        String nombre = "Receta de prueba";
        String tipoCocina = "Italiana";
        String ingredientes = "Tomate, Queso";
        String paisOrigen = "Italia";
        String dificultad = "Media";

        Filtro filtro = new Filtro(nombre, tipoCocina, ingredientes, paisOrigen, dificultad);

        assertEquals(nombre, filtro.getNombre());
        assertEquals(tipoCocina, filtro.getTipoCocina());
        assertEquals(ingredientes, filtro.getIngredientes());
        assertEquals(paisOrigen, filtro.getPaisOrigen());
        assertEquals(dificultad, filtro.getDificultad());
    }

    @Test
    void testSettersAndGetters() {
        Filtro filtro = new Filtro();

        String nombre = "Sopa de lentejas";
        String tipoCocina = "Casera";
        String ingredientes = "Lentejas, Zanahoria";
        String paisOrigen = "Chile";
        String dificultad = "Fácil";

        filtro.setNombre(nombre);
        filtro.setTipoCocina(tipoCocina);
        filtro.setIngredientes(ingredientes);
        filtro.setPaisOrigen(paisOrigen);
        filtro.setDificultad(dificultad);

        assertEquals(nombre, filtro.getNombre());
        assertEquals(tipoCocina, filtro.getTipoCocina());
        assertEquals(ingredientes, filtro.getIngredientes());
        assertEquals(paisOrigen, filtro.getPaisOrigen());
        assertEquals(dificultad, filtro.getDificultad());
    }

    @Test
    void testNullValues() {
        Filtro filtro = new Filtro();

        filtro.setNombre(null);
        filtro.setTipoCocina(null);
        filtro.setIngredientes(null);
        filtro.setPaisOrigen(null);
        filtro.setDificultad(null);

        assertNull(filtro.getNombre());
        assertNull(filtro.getTipoCocina());
        assertNull(filtro.getIngredientes());
        assertNull(filtro.getPaisOrigen());
        assertNull(filtro.getDificultad());
    }
}
