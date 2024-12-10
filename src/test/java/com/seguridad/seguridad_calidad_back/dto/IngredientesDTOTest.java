package com.seguridad.seguridad_calidad_back.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IngredientesDTOTest {

    @Test
    void testDefaultConstructor() {
        IngredientesDTO ingredientesDTO = new IngredientesDTO();

        assertNull(ingredientesDTO.getId());
        assertNull(ingredientesDTO.getNombreIngrediente());
    }

    @Test
    void testSettersAndGetters() {
        IngredientesDTO ingredientesDTO = new IngredientesDTO();

        Long id = 1L;
        String nombreIngrediente = "Sal";

        ingredientesDTO.setId(id);
        ingredientesDTO.setNombreIngrediente(nombreIngrediente);

        assertEquals(id, ingredientesDTO.getId());
        assertEquals(nombreIngrediente, ingredientesDTO.getNombreIngrediente());
    }

    @Test
    void testEquality() {
        IngredientesDTO ingrediente1 = new IngredientesDTO();
        IngredientesDTO ingrediente2 = new IngredientesDTO();

        ingrediente1.setId(1L);
        ingrediente1.setNombreIngrediente("Sal");

        ingrediente2.setId(1L);
        ingrediente2.setNombreIngrediente("Sal");

        assertEquals(ingrediente1.getId(), ingrediente2.getId());
        assertEquals(ingrediente1.getNombreIngrediente(), ingrediente2.getNombreIngrediente());
    }

    @Test
    void testNullValues() {
        IngredientesDTO ingredientesDTO = new IngredientesDTO();

        ingredientesDTO.setId(null);
        ingredientesDTO.setNombreIngrediente(null);

        assertNull(ingredientesDTO.getId());
        assertNull(ingredientesDTO.getNombreIngrediente());
    }
}
