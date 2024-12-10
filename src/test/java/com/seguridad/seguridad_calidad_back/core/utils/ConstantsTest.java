package com.seguridad.seguridad_calidad_back.core.utils;

import org.junit.jupiter.api.Test;

import java.security.Key;

import static org.junit.jupiter.api.Assertions.*;

class ConstantsTest {

    @Test
    void testGetSigningKeyB64() {
        // Arrange
        String secretB64 = Constants.SUPER_KEY;

        // Act
        Key key = Constants.getSigningKeyB64(secretB64);

        // Assert
        assertNotNull(key);
        assertEquals("HmacSHA512", key.getAlgorithm());
    }

    @Test
    void testGetSigningKey() {
        // Arrange
        String secret = "this_is_a_test_secret_that_is_long_enough";

        // Act
        Key key = Constants.getSigningKey(secret);

        // Assert
        assertNotNull(key);
        assertEquals("HmacSHA256", key.getAlgorithm());
    }

    @Test
    void testConstantsValues() {
        // Assert
        assertEquals("Authorization", Constants.HEADER_AUTHORIZACION_KEY);
        assertEquals("Bearer ", Constants.TOKEN_BEARER_PREFIX);
        assertEquals("https://www.duocuc.cl/", Constants.ISSUER_INFO);
        assertEquals(864_000_000, Constants.TOKEN_EXPIRATION_TIME);
        assertEquals("/login", Constants.LOGIN_URL);
        assertEquals("/registrar", Constants.REGISTER_URL);
    }
}