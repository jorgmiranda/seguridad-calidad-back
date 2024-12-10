package com.seguridad.seguridad_calidad_back.model;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class IngredienteTest {

    @Test
    void testDefaultConstructor() {
        // Act
        Ingrediente ingrediente = new Ingrediente();

        // Assert
        assertNull(ingrediente.getId());
        assertNull(ingrediente.getNombre());
        assertNull(ingrediente.getRecetaIngredientes());
    }

    @Test
    void testParameterizedConstructor() {
        // Arrange
        Long id = 1L;
        String nombre = "Tomate";
        Set<RecetaIngrediente> recetaIngredientes = new HashSet<>();

        // Act
        Ingrediente ingrediente = new Ingrediente(id, nombre, recetaIngredientes);

        // Assert
        assertEquals(id, ingrediente.getId());
        assertEquals(nombre, ingrediente.getNombre());
        assertEquals(recetaIngredientes, ingrediente.getRecetaIngredientes());
    }

    @Test
    void testSetAndGetId() {
        // Arrange
        Ingrediente ingrediente = new Ingrediente();
        Long id = 2L;

        // Act
        ingrediente.setId(id);

        // Assert
        assertEquals(id, ingrediente.getId());
    }

    @Test
    void testSetAndGetNombre() {
        // Arrange
        Ingrediente ingrediente = new Ingrediente();
        String nombre = "Cebolla";

        // Act
        ingrediente.setNombre(nombre);

        // Assert
        assertEquals(nombre, ingrediente.getNombre());
    }

    @Test
    void testSetAndGetRecetaIngredientes() {
        // Arrange
        Ingrediente ingrediente = new Ingrediente();
        Set<RecetaIngrediente> recetaIngredientes = new HashSet<>();
        RecetaIngrediente recetaIngrediente = new RecetaIngrediente();
        recetaIngredientes.add(recetaIngrediente);

        // Act
        ingrediente.setRecetaIngredientes(recetaIngredientes);

        // Assert
        assertEquals(recetaIngredientes, ingrediente.getRecetaIngredientes());
    }
}
