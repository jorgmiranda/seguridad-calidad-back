package com.seguridad.seguridad_calidad_back.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RecetaIngredienteTest {

    @Test
    void testGettersAndSetters() {
        // Arrange
        RecetaIngredienteId id = new RecetaIngredienteId(1L, 2L);
        Receta receta = new Receta();
        receta.setId(1L);
        receta.setNombre("Tarta de Manzana");
        Ingrediente ingrediente = new Ingrediente();
        ingrediente.setId(2L);
        ingrediente.setNombre("Manzana");

        RecetaIngrediente recetaIngrediente = new RecetaIngrediente();

        // Act
        recetaIngrediente.setId(id);
        recetaIngrediente.setReceta(receta);
        recetaIngrediente.setIngrediente(ingrediente);

        // Assert
        assertEquals(id, recetaIngrediente.getId());
        assertEquals(receta, recetaIngrediente.getReceta());
        assertEquals(ingrediente, recetaIngrediente.getIngrediente());
        assertEquals("Manzana", recetaIngrediente.getNombreIngrediente());
    }

    @Test
    void testConstructorWithParameters() {
        // Arrange
        RecetaIngredienteId id = new RecetaIngredienteId(3L, 4L);
        Receta receta = new Receta();
        receta.setId(3L);
        receta.setNombre("Paella");
        Ingrediente ingrediente = new Ingrediente();
        ingrediente.setId(4L);
        ingrediente.setNombre("Arroz");

        // Act
        RecetaIngrediente recetaIngrediente = new RecetaIngrediente(id, receta, ingrediente);

        // Assert
        assertEquals(id, recetaIngrediente.getId());
        assertEquals(receta, recetaIngrediente.getReceta());
        assertEquals(ingrediente, recetaIngrediente.getIngrediente());
        assertEquals("Arroz", recetaIngrediente.getNombreIngrediente());
    }

    @Test
    void testGetNombreIngredienteWithNullIngrediente() {
        // Arrange
        RecetaIngrediente recetaIngrediente = new RecetaIngrediente();

        // Act
        String nombreIngrediente = recetaIngrediente.getNombreIngrediente();

        // Assert
        assertNull(nombreIngrediente);
    }

    @Test
    void testDefaultConstructor() {
        // Act
        RecetaIngrediente recetaIngrediente = new RecetaIngrediente();

        // Assert
        assertNotNull(recetaIngrediente);
        assertNull(recetaIngrediente.getId());
        assertNull(recetaIngrediente.getReceta());
        assertNull(recetaIngrediente.getIngrediente());
    }
}
