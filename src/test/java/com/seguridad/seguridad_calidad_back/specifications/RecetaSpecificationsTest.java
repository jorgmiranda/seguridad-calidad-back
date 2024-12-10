package com.seguridad.seguridad_calidad_back.specifications;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.seguridad.seguridad_calidad_back.model.Ingrediente;
import com.seguridad.seguridad_calidad_back.model.Receta;
import com.seguridad.seguridad_calidad_back.model.RecetaIngrediente;

class RecetaSpecificationsTest {

    private Root<Receta> root;
    private CriteriaQuery<?> query;
    private CriteriaBuilder builder;

    @BeforeEach
    void setUp() {
        root = mock(Root.class);
        query = mock(CriteriaQuery.class);
        builder = mock(CriteriaBuilder.class);
    }

    @Test
    void testNombreContains_NotNullOrEmpty() {
        String nombre = "Pizza";
        when(builder.like(root.get("nombre"), "%" + nombre + "%")).thenReturn(mock(Predicate.class));

        Predicate predicate = RecetaSpecifications.nombreContains(nombre).toPredicate(root, query, builder);

        assertNotNull(predicate);
        verify(builder, times(1)).like(root.get("nombre"), "%" + nombre + "%");
    }

    @Test
    void testNombreContains_NullOrEmpty() {
        Predicate predicateNull = RecetaSpecifications.nombreContains(null).toPredicate(root, query, builder);
        Predicate predicateEmpty = RecetaSpecifications.nombreContains("").toPredicate(root, query, builder);

        assertNull(predicateNull);
        assertNull(predicateEmpty);
    }

    @Test
    void testPaisOrigenEquals_NotNullOrEmpty() {
        String pais = "Italia";
        when(builder.equal(root.get("paisDeOrigen"), pais)).thenReturn(mock(Predicate.class));

        Predicate predicate = RecetaSpecifications.paisOrigenEquals(pais).toPredicate(root, query, builder);

        assertNotNull(predicate);
        verify(builder, times(1)).equal(root.get("paisDeOrigen"), pais);
    }

    @Test
    void testPaisOrigenEquals_NullOrEmpty() {
        Predicate predicateNull = RecetaSpecifications.paisOrigenEquals(null).toPredicate(root, query, builder);
        Predicate predicateEmpty = RecetaSpecifications.paisOrigenEquals("").toPredicate(root, query, builder);

        assertNull(predicateNull);
        assertNull(predicateEmpty);
    }

    @Test
    void testDificultadEquals_NotNullOrEmpty() {
        String dificultad = "Media";
        when(builder.equal(root.get("dificultadElaboracion"), dificultad)).thenReturn(mock(Predicate.class));

        Predicate predicate = RecetaSpecifications.dificultadEquals(dificultad).toPredicate(root, query, builder);

        assertNotNull(predicate);
        verify(builder, times(1)).equal(root.get("dificultadElaboracion"), dificultad);
    }

    @Test
    void testDificultadEquals_NullOrEmpty() {
        Predicate predicateNull = RecetaSpecifications.dificultadEquals(null).toPredicate(root, query, builder);
        Predicate predicateEmpty = RecetaSpecifications.dificultadEquals("").toPredicate(root, query, builder);

        assertNull(predicateNull);
        assertNull(predicateEmpty);
    }

    @Test
    void testTipoEquals_NotNullOrEmpty() {
        String tipo = "Italiana";
        when(builder.equal(root.get("tipoDeCocina"), tipo)).thenReturn(mock(Predicate.class));

        Predicate predicate = RecetaSpecifications.tipoEquals(tipo).toPredicate(root, query, builder);

        assertNotNull(predicate);
        verify(builder, times(1)).equal(root.get("tipoDeCocina"), tipo);
    }

    @Test
    void testTipoEquals_NullOrEmpty() {
        Predicate predicateNull = RecetaSpecifications.tipoEquals(null).toPredicate(root, query, builder);
        Predicate predicateEmpty = RecetaSpecifications.tipoEquals("").toPredicate(root, query, builder);

        assertNull(predicateNull);
        assertNull(predicateEmpty);
    }

    @Test
    void testIngredienteContains_NotNull() {
        String nombreIngrediente = "Tomate";

        @SuppressWarnings("unchecked")
        Join<Object, Object> recetaIngredientesJoin = mock(Join.class);

        @SuppressWarnings("unchecked")
        Join<Object, Object> ingredienteJoin = mock(Join.class);

        when(root.join("recetaIngredientes")).thenReturn(recetaIngredientesJoin);
        when(recetaIngredientesJoin.join("ingrediente")).thenReturn(ingredienteJoin);
        when(builder.like(ingredienteJoin.get("nombre"), "%" + nombreIngrediente + "%"))
                .thenReturn(mock(Predicate.class));

        Predicate predicate = RecetaSpecifications.ingredienteContains(nombreIngrediente)
                .toPredicate(root, query, builder);

        assertNotNull(predicate);
        verify(root, times(1)).join("recetaIngredientes");
        verify(recetaIngredientesJoin, times(1)).join("ingrediente");
        verify(builder, times(1)).like(ingredienteJoin.get("nombre"), "%" + nombreIngrediente + "%");
    }

    @Test
    void testIngredienteContains_Null() {
        // Arrange
        @SuppressWarnings("unchecked")
        Join<Object, Object> recetaIngredientesJoin = mock(Join.class);
        @SuppressWarnings("unchecked")
        Join<Object, Object> ingredienteJoin = mock(Join.class);
    
        // Mock del comportamiento
        when(root.join("recetaIngredientes")).thenReturn(recetaIngredientesJoin);
        when(recetaIngredientesJoin.join("ingrediente")).thenReturn(ingredienteJoin);
    
        // Act
        Predicate predicate = RecetaSpecifications.ingredienteContains(null).toPredicate(root, query, builder);
    
        // Assert
        assertNull(predicate); // Verifica que el resultado sea null
    }
    



}
