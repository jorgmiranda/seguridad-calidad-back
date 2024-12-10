package com.seguridad.seguridad_calidad_back.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ResponseModelTest {

    @Test
    void testGettersAndSetters() {
        // Arrange
        ResponseModel response = new ResponseModel();
        String messageResponse = "Operation successful";
        Object data = "Sample Data";
        String error = "No error";

        // Act
        response.setMessageResponse(messageResponse);
        response.setData(data);
        response.setError(error);

        // Assert
        assertEquals(messageResponse, response.getMessageResponse());
        assertEquals(data, response.getData());
        assertEquals(error, response.getError());
    }

    @Test
    void testEmptyConstructor() {
        // Act
        ResponseModel response = new ResponseModel();

        // Assert
        assertNotNull(response);
        assertNull(response.getMessageResponse());
        assertNull(response.getData());
        assertNull(response.getError());
    }

    @Test
    void testParameterizedConstructor() {
        // Arrange
        String messageResponse = "Success";
        Object data = 42; // Example data
        String error = null;

        // Act
        ResponseModel response = new ResponseModel(messageResponse, data, error);

        // Assert
        assertEquals(messageResponse, response.getMessageResponse());
        assertEquals(data, response.getData());
        assertNull(response.getError());
    }
}
