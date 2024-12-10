package com.seguridad.seguridad_calidad_back.service.serviceImpl;

import com.seguridad.seguridad_calidad_back.core.exceptions.BadRequestException;
import com.seguridad.seguridad_calidad_back.core.exceptions.EmailAlreadyRegisteredException;
import com.seguridad.seguridad_calidad_back.core.exceptions.InvalidCredentialsException;
import com.seguridad.seguridad_calidad_back.core.exceptions.UserNotFoundException;
import com.seguridad.seguridad_calidad_back.core.utils.JWTAuthtenticationConfig;
import com.seguridad.seguridad_calidad_back.model.LoginResponse;
import com.seguridad.seguridad_calidad_back.model.ResponseModel;
import com.seguridad.seguridad_calidad_back.model.UserModel;
import com.seguridad.seguridad_calidad_back.repository.UserRepository;
import com.seguridad.seguridad_calidad_back.service.serviceImpl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private JWTAuthtenticationConfig jwtAuthtenticationConfig;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllUsers() {
        // Arrange
        List<UserModel> users = new ArrayList<>();
        users.add(new UserModel());
        when(userRepository.findAll()).thenReturn(users);

        // Act
        List<UserModel> result = userService.getAllUsers();

        // Assert
        assertEquals(1, result.size());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void testGetUserById() {
        // Arrange
        UserModel user = new UserModel();
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        // Act
        Optional<UserModel> result = userService.getUserById(1L);

        // Assert
        assertTrue(result.isPresent());
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    void testRegisterUser_Success() {
        // Arrange
        UserModel user = new UserModel();
        user.setEmail("test@test.com");
        user.setPassword("password");
        user.setNombre("Test");
        when(userRepository.findByCorreo(user.getEmail())).thenReturn(null);
        when(userRepository.save(user)).thenReturn(user);

        // Act
        ResponseModel response = userService.registerUser(user);

        // Assert
        assertNotNull(response);
        assertEquals("usuario registrado con exito", response.getMessageResponse());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void testRegisterUser_MissingFields() {
        // Arrange
        UserModel user = new UserModel(); // Sin campos

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> userService.registerUser(user));
        assertTrue(exception.getCause() instanceof BadRequestException); // Verifica que la causa es BadRequestException
        assertTrue(exception.getCause().getMessage().contains("Faltan los valores o campos"));
    }
    @Test
    void testRegisterUser_EmailAlreadyRegistered() {
        // Arrange
        UserModel user = new UserModel();
        user.setEmail("test@test.com");
        user.setPassword("password");
        user.setNombre("Test User");

        when(userRepository.findByCorreo(user.getEmail())).thenReturn(new UserModel());

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> userService.registerUser(user));
        assertTrue(exception.getCause() instanceof EmailAlreadyRegisteredException); // Verifica que la causa es EmailAlreadyRegisteredException
        assertTrue(exception.getCause().getMessage().contains("Email ya registtrado")); // Valida el mensaje
    }

    @Test
    void testFindByEmail_Success() {
        // Arrange
        String email = "test@test.com";
        String password = "password";
        UserModel user = new UserModel();
        user.setEmail(email);
        user.setPassword(password);
        when(userRepository.findByCorreo(email)).thenReturn(user);
        when(jwtAuthtenticationConfig.getJWTToken(email)).thenReturn("mockToken");

        // Act
        ResponseModel response = userService.findByEmail(email, password);

        // Assert
        assertNotNull(response);
        assertEquals("Bienvenido!", response.getMessageResponse());
        LoginResponse loginResponse = (LoginResponse) response.getData();
        assertEquals("mockToken", loginResponse.getToken());
        verify(userRepository, times(1)).findByCorreo(email);
        verify(jwtAuthtenticationConfig, times(1)).getJWTToken(email);
    }

    @Test
    void testFindByEmail_UserNotFound() {
        // Arrange
        String email = "test@test.com";
        when(userRepository.findByCorreo(email)).thenReturn(null);

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> userService.findByEmail(email, "password"));
        assertTrue(exception.getCause() instanceof UserNotFoundException); // Verifica que la causa es UserNotFoundException
        assertTrue(exception.getCause().getMessage().contains("Correo: test@test.com no se encuentra registrado")); // Valida el mensaje
    }

    @Test
    void testFindByEmail_InvalidCredentials() {
        // Arrange
        String email = "test@test.com";
        String password = "wrongPassword";
        UserModel user = new UserModel();
        user.setEmail(email);
        user.setPassword("password"); // Contraseña incorrecta

        when(userRepository.findByCorreo(email)).thenReturn(user);

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> userService.findByEmail(email, password));
        assertTrue(exception.getCause() instanceof InvalidCredentialsException); // Verifica que la causa es InvalidCredentialsException
        assertTrue(exception.getCause().getMessage().contains("Credenciales incorrectas!")); // Verifica el mensaje
    }

    @Test
    void testDeleteUserById() {
        // Arrange
        Long userId = 1L;

        // Act
        userService.deleteUserById(userId);

        // Assert
        verify(userRepository, times(1)).deleteById(userId);
    }
}
