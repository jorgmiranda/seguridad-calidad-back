package com.seguridad.seguridad_calidad_back.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.seguridad.seguridad_calidad_back.model.ResponseModel;
import com.seguridad.seguridad_calidad_back.model.UserModel;
import com.seguridad.seguridad_calidad_back.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;


@WebMvcTest(LoginController.class)
@Import(TestSecurityConfig.class)
public class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testLoginSuccess() throws Exception {
        // Configurar datos simulados
        String correo = "test@example.com";
        String password = "password123";
        ResponseModel response = new ResponseModel("Login exitoso", null, null);

        when(userService.findByEmail(correo, password)).thenReturn(response);

        // Realizar la solicitud y verificar los resultados
        mockMvc.perform(post("/api/login")
                .param("correo", correo)
                .param("password", password)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andDo(MockMvcResultHandlers.print()) 
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.messageResponse").value("Login exitoso"));

        // Verificar interacción con el servicio
        verify(userService, times(1)).findByEmail(correo, password);
    }

    @Test
    void testLoginFailure() throws Exception {
        // Configurar datos simulados
        String correo = "wrong@example.com";
        String password = "wrongpassword";
        ResponseModel response = new ResponseModel("Error al intentar login", null, "Invalid credentials");

        when(userService.findByEmail(correo, password)).thenThrow(new RuntimeException("Invalid credentials"));

        // Realizar la solicitud y verificar los resultados
        mockMvc.perform(post("/api/login")
                .param("correo", correo)
                .param("password", password)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andDo(MockMvcResultHandlers.print()) 
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.messageResponse").value("Error al intentar login"))
                .andExpect(jsonPath("$.error").value("Invalid credentials"));

        // Verificar interacción con el servicio
        verify(userService, times(1)).findByEmail(correo, password);
    }

    @Test
    void testGetUsuarios() throws Exception {
        // Configurar datos simulados
        UserModel user1 = new UserModel();
        user1.setId(1L);
        user1.setEmail("user1@example.com");
        user1.setNombre("User One");
        user1.setPassword("password1");

        UserModel user2 = new UserModel();
        user2.setId(2L);
        user2.setEmail("user2@example.com");
        user2.setNombre("User Two");
        user2.setPassword("password2");

        List<UserModel> users = Arrays.asList(user1, user2);

        when(userService.getAllUsers()).thenReturn(users);

        // Realizar la solicitud y verificar los resultados
        mockMvc.perform(get("/api/usuarios")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].email").value("user1@example.com"))
                .andExpect(jsonPath("$[1].email").value("user2@example.com"));

        // Verificar interacción con el servicio
        verify(userService, times(1)).getAllUsers();
    }

    // @Test
    // void testRegisterUser() throws Exception {
    //     // Configurar datos simulados
    //     UserModel user = new UserModel();
    //     user.setEmail("newuser@example.com");
    //     user.setNombre("New User");
    //     user.setPassword("newpassword");
        

    //     ResponseModel response = new ResponseModel("Usuario registrado con éxito", null, null);

    //     when(userService.registerUser(any(UserModel.class))).thenReturn(response);

    //     // Realizar la solicitud y verificar los resultados
    //     mockMvc.perform(post("/api/registrar")
    //             .content(objectMapper.writeValueAsString(user))
    //             .header("Content-Type", "application/json")
    //             .contentType(MediaType.APPLICATION_JSON))
    //             .andDo(MockMvcResultHandlers.print())
    //             .andExpect(status().isOk())
    //             .andExpect(jsonPath("$.message").value("Usuario registrado con éxito"));

    //     // Verificar interacción con el servicio
    //     verify(userService, times(1)).registerUser(any(UserModel.class));
    // }
}
