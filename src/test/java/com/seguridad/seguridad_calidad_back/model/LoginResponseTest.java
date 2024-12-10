package com.seguridad.seguridad_calidad_back.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginResponseTest {

    @Test
    void testDefaultConstructor() {
        // Act
        LoginResponse loginResponse = new LoginResponse();

        // Assert
        assertNotNull(loginResponse);
        assertNull(loginResponse.getToken());
        assertNull(loginResponse.getUser());
    }

    @Test
    void testSettersAndGetters() {
        // Arrange
        LoginResponse loginResponse = new LoginResponse();
        String token = "sampleToken123";
        UserModel user = new UserModel();
        user.setId(1L);
        user.setNombre("Test User");
        user.setEmail("test@example.com");

        // Act
        loginResponse.setToken(token);
        loginResponse.setUser(user);

        // Assert
        assertEquals(token, loginResponse.getToken());
        assertEquals(user, loginResponse.getUser());
    }

    @Test
    void testTokenGetterSetter() {
        // Arrange
        LoginResponse loginResponse = new LoginResponse();
        String token = "Bearer abc123";

        // Act
        loginResponse.setToken(token);

        // Assert
        assertEquals(token, loginResponse.getToken());
    }

    @Test
    void testUserGetterSetter() {
        // Arrange
        LoginResponse loginResponse = new LoginResponse();
        UserModel user = new UserModel();
        user.setId(1L);
        user.setNombre("Sample User");

        // Act
        loginResponse.setUser(user);

        // Assert
        assertEquals(user, loginResponse.getUser());
    }

    @Test
    void testEquality() {
        // Arrange
        LoginResponse response1 = new LoginResponse();
        response1.setToken("token123");
        UserModel user1 = new UserModel();
        user1.setId(1L);
        user1.setEmail("user@example.com");
        user1.setPassword("password123");
        response1.setUser(user1);

        LoginResponse response2 = new LoginResponse();
        response2.setToken("token123");
        UserModel user2 = new UserModel();
        user2.setId(1L);
        user2.setEmail("user@example.com");
        user2.setPassword("password123");
        response2.setUser(user2);

        // Assert
        assertEquals(response1.getToken(), response2.getToken());
        assertEquals(user1.getId(), user2.getId());
        assertEquals(user1.getEmail(), user2.getEmail());
        assertEquals(user1.getPassword(), user2.getPassword());
    }
}
