package com.seguridad.seguridad_calidad_back.service.serviceImpl;

import com.seguridad.seguridad_calidad_back.core.exceptions.EmptyCommentException;
import com.seguridad.seguridad_calidad_back.core.exceptions.InvalidCalificationValueException;
import com.seguridad.seguridad_calidad_back.dto.RecetaDTO;
import com.seguridad.seguridad_calidad_back.model.*;
import com.seguridad.seguridad_calidad_back.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.data.jpa.domain.Specification;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class RecetaServiceImplTest {

    @InjectMocks
    private RecetaServiceImpl recetaService;

    @Mock
    private RecetaRepository recetaRepository;

    @Mock
    private IngredienteRepository ingredienteRepository;

    @Mock
    private RecipeCommentRepository recipeCommentRepository;

    @Mock
    private RecipeCalificationRepository recipeCalificationRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllRecetas() {
        List<Receta> recetas = Arrays.asList(new Receta(), new Receta());
        when(recetaRepository.findAll()).thenReturn(recetas);

        List<Receta> result = recetaService.getAllRecetas();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(recetaRepository, times(1)).findAll();
    }

    @Test
    void testFiltrarRecetas() {
        List<Receta> recetas = Arrays.asList(new Receta());
        when(recetaRepository.findAll(any(Specification.class))).thenReturn(recetas);

        List<Receta> result = recetaService.filtrarRecetas("nombre", "pais", "dificultad", "tipo", "ingrediente");

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(recetaRepository, times(1)).findAll(any(Specification.class));
    }

    @Test
    void testCrearReceta_Success() {
        RecetaDTO recetaDTO = new RecetaDTO();
        recetaDTO.setNombre("Receta de prueba");
        recetaDTO.setIngredientes(Arrays.asList("Ingrediente1", "Ingrediente2"));
        UserModel usuario = new UserModel();
        Ingrediente ingrediente1 = new Ingrediente(1L, "Ingrediente1", null);
        Ingrediente ingrediente2 = new Ingrediente(2L, "Ingrediente2", null);

        when(ingredienteRepository.findByNombreIn(anyList())).thenReturn(Arrays.asList(ingrediente1, ingrediente2));
        when(recetaRepository.save(any(Receta.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Receta result = recetaService.crearReceta(recetaDTO, usuario);

        assertNotNull(result);
        assertEquals("Receta de prueba", result.getNombre());
        assertEquals(2, result.getRecetaIngredientes().size());
        verify(ingredienteRepository, times(1)).findByNombreIn(anyList());
        verify(recetaRepository, times(2)).save(any(Receta.class));
    }

    @Test
    void testCrearReceta_FailInvalidIngredientes() {
        RecetaDTO recetaDTO = new RecetaDTO();
        recetaDTO.setIngredientes(Arrays.asList("Ingrediente1", "Ingrediente2"));

        when(ingredienteRepository.findByNombreIn(anyList())).thenReturn(Collections.singletonList(new Ingrediente()));

        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                recetaService.crearReceta(recetaDTO, new UserModel()));

        assertEquals("Uno o más ingredientes no existen en el sistema.", exception.getMessage());
        verify(ingredienteRepository, times(1)).findByNombreIn(anyList());
    }

    @Test
    void testAddComment_Success() {
        RecipeComment comment = new RecipeComment();
        comment.setComentario("Comentario válido");
        when(recipeCommentRepository.save(any(RecipeComment.class))).thenReturn(comment);

        ResponseModel result = recetaService.addComment(comment);

        assertNotNull(result);
        assertEquals("Comentario guardado correctamente", result.getMessageResponse());
        verify(recipeCommentRepository, times(1)).save(any(RecipeComment.class));
    }

    @Test
    void testAddComment_EmptyComment() {
        RecipeComment comment = new RecipeComment();
        comment.setComentario("");

        Exception exception = assertThrows(RuntimeException.class, () -> recetaService.addComment(comment));

        assertTrue(exception.getCause() instanceof EmptyCommentException);
        assertEquals("Comentario no puede ser vacio", exception.getCause().getMessage());
        verify(recipeCommentRepository, never()).save(any(RecipeComment.class));
    }

    @Test
    void testAddCalification_Success() {
        RecipeCalification calification = new RecipeCalification();
        calification.setCalificacion(5);
        when(recipeCalificationRepository.save(any(RecipeCalification.class))).thenReturn(calification);

        ResponseModel result = recetaService.addCalification(calification);

        assertNotNull(result);
        assertEquals("Calificacion guardado correctamente", result.getMessageResponse());
        verify(recipeCalificationRepository, times(1)).save(any(RecipeCalification.class));
    }

    @Test
    void testAddCalification_InvalidValue() {
        RecipeCalification calification = new RecipeCalification();
        calification.setCalificacion(-1);

        Exception exception = assertThrows(RuntimeException.class, () -> recetaService.addCalification(calification));

        // Verifica que la causa de la RuntimeException sea InvalidCalificationValueException
        assertTrue(exception.getCause() instanceof InvalidCalificationValueException);
        assertEquals("Calificacion negativo", exception.getCause().getMessage());
        verify(recipeCalificationRepository, never()).save(any(RecipeCalification.class));
    }

    @Test
    void testActualizarReceta_RecetaNoExiste() {
        Long recetaId = 1L;
        RecetaDTO recetaDTO = new RecetaDTO();
        recetaDTO.setIngredientes(List.of("Tomate", "Cebolla"));

        when(recetaRepository.existsById(recetaId)).thenReturn(false);

        Receta result = recetaService.actualizarReceta(recetaId, recetaDTO);

        assertNull(result);
        verify(recetaRepository, never()).findById(anyLong());
    }

    @Test
    void testActualizarReceta_IngredientesInvalidos() {
        Long recetaId = 1L;
        RecetaDTO recetaDTO = new RecetaDTO();
        recetaDTO.setIngredientes(List.of("Tomate", "Cebolla"));

        when(recetaRepository.existsById(recetaId)).thenReturn(true);
        when(ingredienteRepository.findByNombreIn(recetaDTO.getIngredientes())).thenReturn(List.of());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> 
            recetaService.actualizarReceta(recetaId, recetaDTO)
        );

        assertEquals("Uno o más ingredientes no existen en el sistema.", exception.getMessage());
        verify(ingredienteRepository).findByNombreIn(recetaDTO.getIngredientes());
    }

    @Test
    void testActualizarReceta_Exitoso() {
        Long recetaId = 1L;
        RecetaDTO recetaDTO = new RecetaDTO();
        recetaDTO.setIngredientes(List.of("Tomate", "Cebolla"));
        recetaDTO.setNombre("Receta Actualizada");
        recetaDTO.setTipoDeCocina("Mexicana");
        recetaDTO.setPaisDeOrigen("México");
        recetaDTO.setDificultadElaboracion("Fácil");
        recetaDTO.setTiempoCoccion(30);

        Receta receta = new Receta();
        receta.setId(recetaId);
        receta.setRecetaIngredientes(new HashSet<>());

        Ingrediente tomate = new Ingrediente(1L, "Tomate", null);
        Ingrediente cebolla = new Ingrediente(2L, "Cebolla", null);

        when(recetaRepository.existsById(recetaId)).thenReturn(true);
        when(ingredienteRepository.findByNombreIn(recetaDTO.getIngredientes())).thenReturn(List.of(tomate, cebolla));
        when(recetaRepository.findById(recetaId)).thenReturn(Optional.of(receta));
        when(recetaRepository.save(any(Receta.class))).thenReturn(receta);

        Receta result = recetaService.actualizarReceta(recetaId, recetaDTO);

        assertNotNull(result);
        assertEquals("Receta Actualizada", result.getNombre());
        verify(recetaRepository).save(receta);
    }

    @Test
    void testEliminarReceta() {
        Long recetaId = 1L;

        doNothing().when(recetaRepository).deleteById(recetaId);

        recetaService.eliminarReceta(recetaId);

        verify(recetaRepository).deleteById(recetaId);
    }

    @Test
    void testObtenerRecetasPorUsuario() {
        Long usuarioId = 1L;
        Receta receta1 = new Receta();
        receta1.setId(1L);
        receta1.setNombre("Receta 1");

        Receta receta2 = new Receta();
        receta2.setId(2L);
        receta2.setNombre("Receta 2");

        when(recetaRepository.findByUsuarioId(usuarioId)).thenReturn(List.of(receta1, receta2));

        List<Receta> recetas = recetaService.obtenerRecetasPorUsuario(usuarioId);

        assertNotNull(recetas);
        assertEquals(2, recetas.size());
        assertEquals("Receta 1", recetas.get(0).getNombre());
        assertEquals("Receta 2", recetas.get(1).getNombre());
        verify(recetaRepository).findByUsuarioId(usuarioId);
    }

    @Test
    void testGetRecetaByID_Existe() {
        Long recetaId = 1L;
        Receta receta = new Receta();
        receta.setId(recetaId);
        receta.setNombre("Receta de Prueba");

        when(recetaRepository.findById(recetaId)).thenReturn(Optional.of(receta));

        Optional<Receta> result = recetaService.getRecetaByID(recetaId);

        assertTrue(result.isPresent());
        assertEquals("Receta de Prueba", result.get().getNombre());
        verify(recetaRepository).findById(recetaId);
    }

    @Test
    void testGetRecetaByID_NoExiste() {
        Long recetaId = 1L;

        when(recetaRepository.findById(recetaId)).thenReturn(Optional.empty());

        Optional<Receta> result = recetaService.getRecetaByID(recetaId);

        assertFalse(result.isPresent());
        verify(recetaRepository).findById(recetaId);
    }

    @Test
    void testGetCalification_ConCalificaciones() {
        int recetaId = 1;
        RecipeCalification calification1 = new RecipeCalification();
        calification1.setCalificacion(4);

        RecipeCalification calification2 = new RecipeCalification();
        calification2.setCalificacion(5);

        when(recipeCalificationRepository.getAllByIdReceta(recetaId))
            .thenReturn(List.of(calification1, calification2));

        ResponseModel response = recetaService.getCalification(recetaId);

        assertNotNull(response);
        assertEquals(4.5, response.getData());
        assertEquals("Media obtenida correctamente", response.getMessageResponse());
        assertNull(response.getError());
        verify(recipeCalificationRepository).getAllByIdReceta(recetaId);
    }

   

}
