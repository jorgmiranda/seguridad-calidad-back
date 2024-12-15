package com.seguridad.seguridad_calidad_back.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.seguridad.seguridad_calidad_back.dto.Filtro;
import com.seguridad.seguridad_calidad_back.dto.RecetaDTO;
import com.seguridad.seguridad_calidad_back.model.Receta;
import com.seguridad.seguridad_calidad_back.model.RecipeCalification;
import com.seguridad.seguridad_calidad_back.model.RecipeComment;
import com.seguridad.seguridad_calidad_back.model.ResponseModel;
import com.seguridad.seguridad_calidad_back.model.UserModel;
import com.seguridad.seguridad_calidad_back.service.RecetaService;
import com.seguridad.seguridad_calidad_back.service.UserService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@WebMvcTest(RecetaController.class)
@Import(TestSecurityConfig.class)
public class RecetaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RecetaService recetaService;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllRecetas() throws Exception {
        Receta receta1 = new Receta();
        receta1.setId(1L);
        receta1.setNombre("Receta 1");

        Receta receta2 = new Receta();
        receta2.setId(2L);
        receta2.setNombre("Receta 2");

        when(recetaService.getAllRecetas()).thenReturn(Arrays.asList(receta1, receta2));

        mockMvc.perform(get("/recetas/full")
                .with(user("testUser").password("testPass").roles("USER"))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Receta 1"))
                .andExpect(jsonPath("$[1].nombre").value("Receta 2"));

        verify(recetaService, times(1)).getAllRecetas();
    }

    @Test
    void testObtenerRecetasByID() throws Exception {
        Receta receta = new Receta();
        receta.setId(1L);
        receta.setNombre("Receta 1");

        when(recetaService.getRecetaByID(1L)).thenReturn(Optional.of(receta));

        mockMvc.perform(get("/recetas/1")
                .with(user("testUser").password("testPass").roles("USER"))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Receta 1"));

        verify(recetaService, times(1)).getRecetaByID(1L);
    }

    @Test
    void testGetAllRecetasParcial() throws Exception {
        Receta receta = new Receta();
        receta.setId(1L);
        receta.setNombre("Receta Parcial");
        receta.setTipoDeCocina("Italiana");
        receta.setTiempoCoccion(10);

        when(recetaService.getAllRecetas()).thenReturn(Arrays.asList(receta));

        mockMvc.perform(get("/recetas/parcial")
                .with(user("testUser").password("testPass").roles("USER"))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Receta Parcial"))
                .andExpect(jsonPath("$[0].tipoDeCocina").value("Italiana"));

        verify(recetaService, times(1)).getAllRecetas();
    }

    @Test
    @WithMockUser(username = "testUser", roles = {"USER"})
    void testCrearReceta() throws Exception {
        RecetaDTO recetaDTO = new RecetaDTO();
        recetaDTO.setNombre("Nueva Receta");
        recetaDTO.setUsuarioId(1L);

        UserModel user = new UserModel();
        user.setId(1L);

        Receta receta = new Receta();
        receta.setId(1L);
        receta.setNombre("Nueva Receta");

        when(userService.getUserById(1L)).thenReturn(Optional.of(user));
        when(recetaService.crearReceta(any(RecetaDTO.class), eq(user))).thenReturn(receta);

        mockMvc.perform(post("/recetas")
                .content(objectMapper.writeValueAsString(recetaDTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nombre").value("Nueva Receta"));

        verify(userService, times(1)).getUserById(1L);
        verify(recetaService, times(1)).crearReceta(any(RecetaDTO.class), eq(user));
    }

    @Test
    void testGetComentarios() throws Exception {
        RecipeComment comment1 = new RecipeComment();
        comment1.setComentario("Comentario 1");

        RecipeComment comment2 = new RecipeComment();
        comment2.setComentario("Comentario 2");

        ResponseModel response = new ResponseModel("Success", Arrays.asList(comment1, comment2), null);

        when(recetaService.getCommentsInRecipe(1)).thenReturn(response);

        mockMvc.perform(get("/recetas/obtener-comentarios")
                .param("idReceta", "1")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].comentario").value("Comentario 1"))
                .andExpect(jsonPath("$[1].comentario").value("Comentario 2"));

        verify(recetaService, times(1)).getCommentsInRecipe(1);
    }

    @Test
    void testFiltrarRecetas() throws Exception {
        Filtro filtro = new Filtro();
        filtro.setNombre("Receta Filtrada");

        Receta receta = new Receta();
        receta.setId(1L);
        receta.setNombre("Receta Filtrada");

        // Ajusta el mock para que el quinto argumento sea un String
        when(recetaService.filtrarRecetas(anyString(), anyString(), anyString(), anyString(), anyString()))
                .thenReturn(Arrays.asList(receta));

        mockMvc.perform(post("/recetas/filtrar")
                .content(objectMapper.writeValueAsString(filtro))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                ;

        // Ajusta también la verificación para que coincida con el mock
        verify(recetaService, times(1)).filtrarRecetas("Receta Filtrada", null, null, null, null);
    }

    @Test
    void testEditarReceta() throws Exception {
        RecetaDTO recetaDTO = new RecetaDTO();
        recetaDTO.setNombre("Receta Editada");

        Receta receta = new Receta();
        receta.setId(1L);
        receta.setNombre("Receta Editada");

        when(recetaService.actualizarReceta(eq(1L), any(RecetaDTO.class))).thenReturn(receta);

        mockMvc.perform(post("/recetas/1")
                .content(objectMapper.writeValueAsString(recetaDTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nombre").value("Receta Editada"));

        verify(recetaService, times(1)).actualizarReceta(eq(1L), any(RecetaDTO.class));
    }

    @Test
    void testAddComment() throws Exception {
        RecipeComment comment = new RecipeComment();
        comment.setComentario("Nuevo comentario");

        ResponseModel response = new ResponseModel("Comentario agregado", null, null);

        when(recetaService.addComment(any(RecipeComment.class))).thenReturn(response);

        mockMvc.perform(post("/recetas/comentar")
                .content(objectMapper.writeValueAsString(comment))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.messageResponse").value("Comentario agregado"));

        verify(recetaService, times(1)).addComment(any(RecipeComment.class));
    }

    @Test
    void testAddCalification() throws Exception {
        RecipeCalification calification = new RecipeCalification();
        calification.setCalificacion(5);

        ResponseModel response = new ResponseModel("Calificación agregada", null, null);

        when(recetaService.addCalification(any(RecipeCalification.class))).thenReturn(response);

        mockMvc.perform(post("/recetas/calificar")
                .content(objectMapper.writeValueAsString(calification))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.messageResponse").value("Calificación agregada"));

        verify(recetaService, times(1)).addCalification(any(RecipeCalification.class));
    }

    @Test
    void testGetCalification() throws Exception {
        // Configurar datos simulados
        int idRecipe = 1;
        ResponseModel response = new ResponseModel("Puntuación obtenida", 5, null);

        when(recetaService.getCalification(idRecipe)).thenReturn(response);

        // Realizar la solicitud y verificar los resultados
        mockMvc.perform(get("/recetas/puntuacion")
                .param("idRecipe", String.valueOf(idRecipe))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.messageResponse").value("Puntuación obtenida"))
                .andExpect(jsonPath("$.data").value(5));

        verify(recetaService, times(1)).getCalification(idRecipe);
    }

    @Test
    void testObtenerRecetasPorUsuario() throws Exception {
        // Configurar datos simulados
        Long usuarioId = 1L;
        Receta receta1 = new Receta();
        receta1.setId(1L);
        receta1.setNombre("Receta 1");

        Receta receta2 = new Receta();
        receta2.setId(2L);
        receta2.setNombre("Receta 2");

        List<Receta> recetas = Arrays.asList(receta1, receta2);

        when(recetaService.obtenerRecetasPorUsuario(usuarioId)).thenReturn(recetas);

        // Realizar la solicitud y verificar los resultados
        mockMvc.perform(get("/recetas/usuario/{usuarioId}", usuarioId)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Receta 1"))
                .andExpect(jsonPath("$[1].nombre").value("Receta 2"));

        verify(recetaService, times(1)).obtenerRecetasPorUsuario(usuarioId);
    }

}
