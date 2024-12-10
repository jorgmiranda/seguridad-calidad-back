package com.seguridad.seguridad_calidad_back.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class RecipeCalificationTest {

    @Test
    void testGettersAndSetters() {
        // Arrange
        RecipeCalification recipeCalification = new RecipeCalification();
        long id = 1L;
        int idReceta = 101;
        int idUsuario = 202;
        int calificacion = 5;

        // Act
        recipeCalification.setId(id);
        recipeCalification.setId_receta(idReceta);
        recipeCalification.setId_usuario(idUsuario);
        recipeCalification.setCalificacion(calificacion);

        // Assert
        assertEquals(id, recipeCalification.getId());
        assertEquals(idReceta, recipeCalification.getId_receta());
        assertEquals(idUsuario, recipeCalification.getId_usuario());
        assertEquals(calificacion, recipeCalification.getCalificacion());
    }

    @Test
    void testEmptyConstructor() {
        // Act
        RecipeCalification recipeCalification = new RecipeCalification();

        // Assert
        assertNotNull(recipeCalification);
        assertEquals(0L, recipeCalification.getId());
        assertEquals(0, recipeCalification.getId_receta());
        assertEquals(0, recipeCalification.getId_usuario());
        assertEquals(0, recipeCalification.getCalificacion());
    }
}
