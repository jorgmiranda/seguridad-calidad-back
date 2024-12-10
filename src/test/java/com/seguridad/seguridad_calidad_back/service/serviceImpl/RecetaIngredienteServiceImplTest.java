package com.seguridad.seguridad_calidad_back.service.serviceImpl;

import com.seguridad.seguridad_calidad_back.model.Receta;
import com.seguridad.seguridad_calidad_back.model.RecetaIngrediente;
import com.seguridad.seguridad_calidad_back.model.RecetaIngredienteId;
import com.seguridad.seguridad_calidad_back.model.Ingrediente;
import com.seguridad.seguridad_calidad_back.repository.IngredienteRepository;
import com.seguridad.seguridad_calidad_back.repository.RecetaIngredienteRepository;
import com.seguridad.seguridad_calidad_back.repository.RecetaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RecetaIngredienteServiceImplTest {

    @InjectMocks
    private RecetaIngredienteServiceImpl recetaIngredienteService;

    @Mock
    private RecetaIngredienteRepository recetaIngredienteRepository;

    @Mock
    private RecetaRepository recetaRepository;

    @Mock
    private IngredienteRepository ingredienteRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testObtenerPorReceta() {
        Long recetaId = 1L;
        RecetaIngrediente recetaIngrediente = new RecetaIngrediente();
        when(recetaIngredienteRepository.findByIdRecetaId(recetaId)).thenReturn(Collections.singletonList(recetaIngrediente));

        List<RecetaIngrediente> result = recetaIngredienteService.obtenerPorReceta(recetaId);

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(recetaIngredienteRepository, times(1)).findByIdRecetaId(recetaId);
    }

    @Test
    void testObtenerPorIngrediente() {
        Long ingredienteId = 1L;
        RecetaIngrediente recetaIngrediente = new RecetaIngrediente();
        when(recetaIngredienteRepository.findByIdIngredienteId(ingredienteId)).thenReturn(Collections.singletonList(recetaIngrediente));

        List<RecetaIngrediente> result = recetaIngredienteService.obtenerPorIngrediente(ingredienteId);

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(recetaIngredienteRepository, times(1)).findByIdIngredienteId(ingredienteId);
    }

    @Test
    void testGuardarRecetaIngrediente() {
        RecetaIngrediente recetaIngrediente = new RecetaIngrediente();
        when(recetaIngredienteRepository.save(recetaIngrediente)).thenReturn(recetaIngrediente);

        RecetaIngrediente result = recetaIngredienteService.guardarRecetaIngrediente(recetaIngrediente);

        assertNotNull(result);
        verify(recetaIngredienteRepository, times(1)).save(recetaIngrediente);
    }

    @Test
    void testEliminarRecetaIngrediente() {
        Long recetaId = 1L;
        Long ingredienteId = 1L;
        RecetaIngredienteId id = new RecetaIngredienteId(recetaId, ingredienteId);

        doNothing().when(recetaIngredienteRepository).deleteById(id);

        recetaIngredienteService.eliminarRecetaIngrediente(recetaId, ingredienteId);

        verify(recetaIngredienteRepository, times(1)).deleteById(id);
    }

    @Test
    void testActualizarIngredientesDeReceta() {
        Long recetaId = 1L;
        List<Long> nuevosIngredientesIds = Arrays.asList(2L, 3L);

        Receta receta = new Receta();
        Ingrediente ingrediente1 = new Ingrediente();
        ingrediente1.setId(2L);
        Ingrediente ingrediente2 = new Ingrediente();
        ingrediente2.setId(3L);

        when(recetaIngredienteRepository.findByIdRecetaId(recetaId)).thenReturn(Collections.emptyList());
        when(recetaRepository.findById(recetaId)).thenReturn(Optional.of(receta));
        when(ingredienteRepository.findById(2L)).thenReturn(Optional.of(ingrediente1));
        when(ingredienteRepository.findById(3L)).thenReturn(Optional.of(ingrediente2));

        recetaIngredienteService.actualizarIngredientesDeReceta(recetaId, nuevosIngredientesIds);

        verify(recetaIngredienteRepository, times(1)).deleteAll(Collections.emptyList());
        verify(recetaIngredienteRepository, times(1)).saveAll(anyList());
    }

    @Test
    void testActualizarIngredientesDeReceta_RecetaNoEncontrada() {
        Long recetaId = 1L;
        List<Long> nuevosIngredientesIds = Arrays.asList(2L, 3L);

        when(recetaRepository.findById(recetaId)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                recetaIngredienteService.actualizarIngredientesDeReceta(recetaId, nuevosIngredientesIds));

        assertEquals("Receta no encontrada", exception.getMessage());
        verify(recetaRepository, times(1)).findById(recetaId);
        verifyNoInteractions(ingredienteRepository);
    }

    @Test
    void testActualizarIngredientesDeReceta_IngredienteNoEncontrado() {
        Long recetaId = 1L;
        List<Long> nuevosIngredientesIds = Arrays.asList(2L);

        Receta receta = new Receta();
        when(recetaRepository.findById(recetaId)).thenReturn(Optional.of(receta));
        when(ingredienteRepository.findById(2L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                recetaIngredienteService.actualizarIngredientesDeReceta(recetaId, nuevosIngredientesIds));

        assertEquals("Ingrediente no encontrado", exception.getMessage());
        verify(recetaRepository, times(1)).findById(recetaId);
        verify(ingredienteRepository, times(1)).findById(2L);
    }
}
