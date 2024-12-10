package com.seguridad.seguridad_calidad_back.core.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.IOException;
import java.util.Collections;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

class JWTAuthorizationFilterTest {

    @InjectMocks
    private JWTAuthorizationFilter jwtAuthorizationFilter;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private FilterChain filterChain;

    private String token;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Generar un token válido
        token = Jwts.builder()
                .setSubject("user@test.com")
                .claim("authorities", Collections.singletonList("ROLE_USER"))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 10)) // 10 minutos
                .signWith(Constants.getSigningKey(Constants.SUPER_KEY), SignatureAlgorithm.HS256)
                .compact();
    }

    @Test
    void testDoFilterInternal_ValidToken() throws ServletException, IOException {
        // Configurar el request con un token válido
        when(request.getHeader(AUTHORIZATION)).thenReturn("Bearer " + token);

        // Ejecutar el filtro
        jwtAuthorizationFilter.doFilterInternal(request, response, filterChain);

        // Verificar que se autenticó el usuario
        assertNotNull(SecurityContextHolder.getContext().getAuthentication());
        assertEquals("user@test.com", SecurityContextHolder.getContext().getAuthentication().getName());

        // Verificar que el filtro continuó con la cadena
        verify(filterChain, times(1)).doFilter(request, response);
    }

    @Test
    void testDoFilterInternal_InvalidToken() throws ServletException, IOException {
        // Configurar el request con un token inválido
        when(request.getHeader(AUTHORIZATION)).thenReturn("Bearer invalid_token");

        // Ejecutar el filtro
        jwtAuthorizationFilter.doFilterInternal(request, response, filterChain);

        // Verificar que el contexto de seguridad se limpió
        assertNull(SecurityContextHolder.getContext().getAuthentication());

        // Verificar que se envió un error de respuesta
        verify(response, times(1)).sendError(eq(HttpServletResponse.SC_FORBIDDEN), anyString());
    }

    @Test
    void testDoFilterInternal_ExpiredToken() throws ServletException, IOException {
        // Generar un token expirado
        String expiredToken = Jwts.builder()
                .setSubject("user@test.com")
                .claim("authorities", Collections.singletonList("ROLE_USER"))
                .setIssuedAt(new Date(System.currentTimeMillis() - 1000 * 60 * 10)) // Expirado hace 10 minutos
                .setExpiration(new Date(System.currentTimeMillis() - 1000 * 60)) // Expirado hace 1 minuto
                .signWith(Constants.getSigningKey(Constants.SUPER_KEY), SignatureAlgorithm.HS256)
                .compact();

        when(request.getHeader(AUTHORIZATION)).thenReturn("Bearer " + expiredToken);

        // Ejecutar el filtro
        jwtAuthorizationFilter.doFilterInternal(request, response, filterChain);

        // Verificar que el contexto de seguridad se limpió
        assertNull(SecurityContextHolder.getContext().getAuthentication());

        // Verificar que se envió un error de respuesta
        verify(response, times(1)).sendError(eq(HttpServletResponse.SC_FORBIDDEN), anyString());
    }

    @Test
    void testDoFilterInternal_NoToken() throws ServletException, IOException {
        // Configurar el request sin un header de autorización
        when(request.getHeader(AUTHORIZATION)).thenReturn(null);

        // Ejecutar el filtro
        jwtAuthorizationFilter.doFilterInternal(request, response, filterChain);

        // Verificar que el contexto de seguridad se limpió
        assertNull(SecurityContextHolder.getContext().getAuthentication());

        // Verificar que el filtro continuó con la cadena
        verify(filterChain, times(1)).doFilter(request, response);
    }
}
