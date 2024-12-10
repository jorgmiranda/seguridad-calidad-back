package com.seguridad.seguridad_calidad_back.service.serviceImpl;

import com.seguridad.seguridad_calidad_back.model.Ingrediente;
import com.seguridad.seguridad_calidad_back.repository.IngredienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class IngredienteServiceImplTest {

    @Mock
    private IngredienteRepository ingredienteRepository;

    @InjectMocks
    private IngredienteServiceImpl ingredienteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllIngredientes() {
        // Arrange
        Ingrediente ingrediente1 = new Ingrediente(1L, "Tomate", null);
        Ingrediente ingrediente2 = new Ingrediente(2L, "Cebolla", null);
        List<Ingrediente> ingredientes = Arrays.asList(ingrediente1, ingrediente2);

        when(ingredienteRepository.findAll()).thenReturn(ingredientes);

        // Act
        List<Ingrediente> result = ingredienteService.getAllIngredientes();

        // Assert
        assertEquals(2, result.size());
        verify(ingredienteRepository, times(1)).findAll();
    }

    @Test
    void testGetIngredienteByID_Found() {
        // Arrange
        Ingrediente ingrediente = new Ingrediente(1L, "Tomate", null);
        when(ingredienteRepository.findById(1L)).thenReturn(Optional.of(ingrediente));

        // Act
        Optional<Ingrediente> result = ingredienteService.getIngredienteByID(1L);

        // Assert
        assertTrue(result.isPresent());
        assertEquals("Tomate", result.get().getNombre());
        verify(ingredienteRepository, times(1)).findById(1L);
    }

    @Test
    void testGetIngredienteByID_NotFound() {
        // Arrange
        when(ingredienteRepository.findById(1L)).thenReturn(Optional.empty());

        // Act
        Optional<Ingrediente> result = ingredienteService.getIngredienteByID(1L);

        // Assert
        assertFalse(result.isPresent());
        verify(ingredienteRepository, times(1)).findById(1L);
    }

    @Test
    void testCrearIngrediente() {
        // Arrange
        Ingrediente ingrediente = new Ingrediente(null, "Tomate", null);
        Ingrediente savedIngrediente = new Ingrediente(1L, "Tomate", null);

        when(ingredienteRepository.save(ingrediente)).thenReturn(savedIngrediente);

        // Act
        Ingrediente result = ingredienteService.crearIngrediente(ingrediente);

        // Assert
        assertNotNull(result.getId());
        assertEquals("Tomate", result.getNombre());
        verify(ingredienteRepository, times(1)).save(ingrediente);
    }

    @Test
    void testActualizarIngrediente_Found() {
        // Arrange
        Ingrediente ingrediente = new Ingrediente(1L, "Tomate", null);

        when(ingredienteRepository.existsById(1L)).thenReturn(true);
        when(ingredienteRepository.save(ingrediente)).thenReturn(ingrediente);

        // Act
        Ingrediente result = ingredienteService.actualizarIngrediente(1L, ingrediente);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Tomate", result.getNombre());
        verify(ingredienteRepository, times(1)).existsById(1L);
        verify(ingredienteRepository, times(1)).save(ingrediente);
    }

    @Test
    void testActualizarIngrediente_NotFound() {
        // Arrange
        Ingrediente ingrediente = new Ingrediente(1L, "Tomate", null);

        when(ingredienteRepository.existsById(1L)).thenReturn(false);

        // Act
        Ingrediente result = ingredienteService.actualizarIngrediente(1L, ingrediente);

        // Assert
        assertNull(result);
        verify(ingredienteRepository, times(1)).existsById(1L);
        verify(ingredienteRepository, never()).save(ingrediente);
    }

    @Test
    void testEliminarIngrediente() {
        // Arrange
        doNothing().when(ingredienteRepository).deleteById(1L);

        // Act
        ingredienteService.eliminarIngrediente(1L);

        // Assert
        verify(ingredienteRepository, times(1)).deleteById(1L);
    }
}
