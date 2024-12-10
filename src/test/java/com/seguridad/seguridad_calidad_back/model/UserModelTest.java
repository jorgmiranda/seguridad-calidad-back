package com.seguridad.seguridad_calidad_back.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.List;

public class UserModelTest {

    @Test
    void testGettersAndSetters() {
        // Arrange
        UserModel user = new UserModel();
        long id = 1L;
        String correo = "test@example.com";
        String contrasena = "password123";
        String nombre = "Test User";
        List<Receta> recetas = List.of(new Receta(), new Receta());

        // Act
        user.setId(id);
        user.setEmail(correo);
        user.setPassword(contrasena);
        user.setNombre(nombre);
        user.setRecetas(recetas);

        // Assert
        assertEquals(id, user.getId());
        assertEquals(correo, user.getEmail());
        assertEquals(contrasena, user.getPassword());
        assertEquals(nombre, user.getNombre());
        assertEquals(recetas, user.getRecetas());
    }

    @Test
    void testEmptyConstructor() {
        // Act
        UserModel user = new UserModel();

        // Assert
        assertNotNull(user);
        assertNull(user.getEmail());
        assertNull(user.getPassword());
        assertNull(user.getNombre());
        assertNull(user.getRecetas());
        assertEquals(0, user.getId());
    }

    @Test
    void testPartialInitialization() {
        // Arrange
        UserModel user = new UserModel();
        user.setEmail("partial@example.com");

        // Act & Assert
        assertEquals("partial@example.com", user.getEmail());
        assertNull(user.getPassword());
        assertNull(user.getNombre());
        assertNull(user.getRecetas());
    }
}
