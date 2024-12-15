package com.seguridad.seguridad_calidad_back.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class RecipeCommentTest {

    @Test
    void testGettersAndSetters() {
        // Arrange
        RecipeComment recipeComment = new RecipeComment();
        long id = 1L;
        int idReceta = 101;
        Long idUsuario = 202L;
        String comentario = "Delicious recipe!";

        // Act
        recipeComment.setId(id);
        recipeComment.setId_receta(idReceta);
        recipeComment.setId_usuario(idUsuario);
        recipeComment.setComentario(comentario);

        // Assert
        assertEquals(id, recipeComment.getId());
        assertEquals(idReceta, recipeComment.getId_receta());
        assertEquals(idUsuario, recipeComment.getId_usuario());
        assertEquals(comentario, recipeComment.getComentario());
    }

    @Test
    void testEmptyConstructor() {
        // Act
        RecipeComment recipeComment = new RecipeComment();

        // Assert
        assertNotNull(recipeComment);
        assertEquals(0L, recipeComment.getId());
        assertEquals(0, recipeComment.getId_receta());
        assertEquals(0, recipeComment.getId_usuario());
        assertNull(recipeComment.getComentario());
    }
}
