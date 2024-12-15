package com.seguridad.seguridad_calidad_back.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.seguridad.seguridad_calidad_back.model.Ingrediente;
import com.seguridad.seguridad_calidad_back.service.IngredienteService;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;


@WebMvcTest(IngredientesController.class)
public class IngredientesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IngredienteService ingredienteService;

    @InjectMocks
    private IngredientesController ingredientesController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllIngredientes() throws Exception {
        // Crear datos simulados del modelo Ingrediente
        Ingrediente ingrediente1 = new Ingrediente();
        ingrediente1.setId(1L);
        ingrediente1.setNombre("Tomate");

        Ingrediente ingrediente2 = new Ingrediente();
        ingrediente2.setId(2L);
        ingrediente2.setNombre("Cebolla");

        // Configurar el comportamiento simulado del servicio
        when(ingredienteService.getAllIngredientes()).thenReturn(Arrays.asList(ingrediente1, ingrediente2));

        // Realizar la solicitud y verificar los resultados
        mockMvc.perform(get("/ingredientes")
                .with(user("testUser").password("testPass").roles("USER"))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].nombreIngrediente").value("Tomate"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].nombreIngrediente").value("Cebolla"));

        // Verificar interacciones con el servicio
        verify(ingredienteService, times(1)).getAllIngredientes();
    }
}
