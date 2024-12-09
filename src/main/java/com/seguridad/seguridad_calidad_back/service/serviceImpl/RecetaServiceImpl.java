package com.seguridad.seguridad_calidad_back.service.serviceImpl;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.seguridad.seguridad_calidad_back.core.exceptions.EmptyCommentException;
import com.seguridad.seguridad_calidad_back.core.exceptions.InvalidCalificationValueException;
import com.seguridad.seguridad_calidad_back.core.exceptions.NullCalificationValueException;
import com.seguridad.seguridad_calidad_back.model.*;
import com.seguridad.seguridad_calidad_back.repository.RecipeCalificationRepository;
import com.seguridad.seguridad_calidad_back.repository.RecipeCommentRepository;
import com.seguridad.seguridad_calidad_back.service.RecetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.seguridad.seguridad_calidad_back.dto.RecetaDTO;
import com.seguridad.seguridad_calidad_back.model.Ingrediente;
import com.seguridad.seguridad_calidad_back.model.Receta;
import com.seguridad.seguridad_calidad_back.model.RecetaIngrediente;
import com.seguridad.seguridad_calidad_back.model.RecetaIngredienteId;
import com.seguridad.seguridad_calidad_back.repository.IngredienteRepository;
import com.seguridad.seguridad_calidad_back.repository.RecetaRepository;
import com.seguridad.seguridad_calidad_back.specifications.RecetaSpecifications;

@Service
public class RecetaServiceImpl implements RecetaService {
    @Autowired
    private RecetaRepository recetaRepository;

    @Autowired
    private IngredienteRepository ingredienteRepository;

    @Autowired
    private RecipeCommentRepository recipeCommentRepository;

    @Autowired
    private RecipeCalificationRepository recipeCalificationRepository;

    @Override
    public List<Receta> getAllRecetas() {
        return recetaRepository.findAll();
    }

    public List<Receta> filtrarRecetas(String nombre, String pais, String dificultad, String tipo, String ingrediente) {
        Specification<Receta> spec = Specification.where(null); // Inicializa como null

        if (nombre != null && !nombre.trim().isEmpty()) {

            spec = spec.and(RecetaSpecifications.nombreContains(nombre));
        }
        if (pais != null && !pais.trim().isEmpty()) {

            spec = spec.and(RecetaSpecifications.paisOrigenEquals(pais));
        }
        if (dificultad != null && !dificultad.trim().isEmpty()) {

            spec = spec.and(RecetaSpecifications.dificultadEquals(dificultad));
        }
        if (tipo != null && !tipo.trim().isEmpty()) {

            spec = spec.and(RecetaSpecifications.tipoEquals(tipo));
        }

        if (ingrediente != null && !ingrediente.isEmpty()) {

            spec = spec.and(RecetaSpecifications.ingredienteContains(ingrediente));
        }

        return recetaRepository.findAll(spec);
    }

    @Override
    public Optional<Receta> getRecetaByID(Long id) {
        return recetaRepository.findById(id);
    }

    @Override
    public Receta crearReceta(RecetaDTO recetaDTO) {

        Set<RecetaIngrediente> recetaIngredientes = new HashSet<>();

        // Obtener todos los ingredientes solicitados
        List<Ingrediente> ingredientesExistentes = ingredienteRepository.findByNombreIn(recetaDTO.getIngredientes());

        // Verificar que todos los ingredientes solicitados existan
        if (ingredientesExistentes.size() != recetaDTO.getIngredientes().size()) {
            throw new IllegalArgumentException("Uno o más ingredientes no existen en el sistema.");
        }

        // Se pasa la información del DTO al la clase model
        Receta receta = new Receta();
        receta.setNombre(recetaDTO.getNombre());
        receta.setTipoDeCocina(recetaDTO.getTipoDeCocina());
        receta.setPaisDeOrigen(recetaDTO.getPaisDeOrigen());
        receta.setDificultadElaboracion(recetaDTO.getDificultadElaboracion());
        receta.setInstruccionesPreparacion(recetaDTO.getInstruccionesPreparacion());
        receta.setTiempoCoccion(recetaDTO.getTiempoCoccion());
        receta.setUrlImagen(recetaDTO.getUrlImagen());
        receta.setPopularidad(recetaDTO.getPopularidad());
        receta.setFechaCreacion(new Date());

        // Se guarda la receta para obtener el id
        receta = recetaRepository.save(receta);

        // Crear las relaciones de receta e ingredientes
        for (Ingrediente ingrediente : ingredientesExistentes) {
            RecetaIngrediente recetaIngrediente = new RecetaIngrediente();
            // Se agrega la clave compuesta
            recetaIngrediente.setId(new RecetaIngredienteId(receta.getId(), ingrediente.getId()));

            recetaIngrediente.setReceta(receta);
            recetaIngrediente.setIngrediente(ingrediente);
            recetaIngredientes.add(recetaIngrediente);
        }

        receta.setRecetaIngredientes(recetaIngredientes);

        return recetaRepository.save(receta);
    }

    @Override
    public Receta actualizarReceta(Long id, RecetaDTO recetaDTO) {
        if (recetaRepository.existsById(id)) {
            Set<RecetaIngrediente> recetaIngredientes = new HashSet<>();

            // Obtener todos los ingredientes solicitados
            List<Ingrediente> ingredientesExistentes = ingredienteRepository
                    .findByNombreIn(recetaDTO.getIngredientes());

            // Verificar que todos los ingredientes solicitados existan
            if (ingredientesExistentes.size() != recetaDTO.getIngredientes().size()) {
                throw new IllegalArgumentException("Uno o más ingredientes no existen en el sistema.");
            }

            Optional<Receta> recetaOptional = recetaRepository.findById(id);
            if (recetaOptional.isPresent()) {
                Receta receta = recetaOptional.get();
                receta.setNombre(recetaDTO.getNombre());
                receta.setTipoDeCocina(recetaDTO.getTipoDeCocina());
                receta.setPaisDeOrigen(recetaDTO.getPaisDeOrigen());
                receta.setDificultadElaboracion(recetaDTO.getDificultadElaboracion());
                receta.setInstruccionesPreparacion(recetaDTO.getInstruccionesPreparacion());
                receta.setTiempoCoccion(recetaDTO.getTiempoCoccion());
                if (recetaDTO.getUrlImagen() != null) {
                    receta.setUrlImagen(recetaDTO.getUrlImagen());
                }

                receta.setUrlVideo(recetaDTO.getUrlVideo());

                // Eliminar ingredientes que ya no están en la lista
                Set<Ingrediente> ingredientesAEliminar = receta.getRecetaIngredientes().stream()
                        .map(RecetaIngrediente::getIngrediente)
                        .filter(ingrediente -> !recetaDTO.getIngredientes().contains(ingrediente.getNombre()))
                        .collect(Collectors.toSet());

                receta.getRecetaIngredientes().removeAll(ingredientesAEliminar);

                // Agregar nuevos ingredientes
                Set<Ingrediente> ingredientesAAgregar = ingredientesExistentes.stream()
                        .filter(ingrediente -> receta.getRecetaIngredientes().stream()
                                .noneMatch(ri -> ri.getIngrediente().equals(ingrediente)))
                        .collect(Collectors.toSet());

                for (Ingrediente ingrediente : ingredientesAAgregar) {
                    RecetaIngrediente recetaIngrediente = new RecetaIngrediente();
                    recetaIngrediente.setId(new RecetaIngredienteId(receta.getId(), ingrediente.getId()));
                    recetaIngrediente.setReceta(receta);
                    recetaIngrediente.setIngrediente(ingrediente);
                    receta.getRecetaIngredientes().add(recetaIngrediente);
                }

                return recetaRepository.save(receta);
            } else {
                return null;
            }

        } else {
            return null;
        }
    }

    @Override
    public void eliminarReceta(Long id) {
        recetaRepository.deleteById(id);
    }

    @Override
    public List<Receta> obtenerRecetasPorUsuario(Long usuarioId) {
        return recetaRepository.findByUsuarioId(usuarioId);
    }

    @Override
    public ResponseModel addComment(RecipeComment comment) {
        try {
            if (comment.getComentario() == null || comment.getComentario().trim().isEmpty()) {
                throw new EmptyCommentException("Comentario no puede ser vacio");
            }

            ResponseModel response = new ResponseModel();

            response.setData(recipeCommentRepository.save(comment));
            response.setError(null);
            response.setMessageResponse("Comentario guardado correctamente");

            return response;
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResponseModel addCalification(RecipeCalification calification) {
        try {
            if (calification.getCalificacion() < 0) {
                throw new InvalidCalificationValueException("Calificacion negativo");
            }

            ResponseModel response = new ResponseModel();

            response.setData(recipeCalificationRepository.save(calification));
            response.setError(null);
            response.setMessageResponse("Calificacion guardado correctamente");

            return response;
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResponseModel getCalification(int id) {
        try {
            List<RecipeCalification> califications = recipeCalificationRepository.getAllByIdReceta(id);

            int addition = 0;
            for (RecipeCalification calification : califications) {
                addition += calification.getCalificacion();
            }

            double media = (double) addition / califications.size();

            ResponseModel response = new ResponseModel();
            response.setData(media);
            response.setError(null);
            response.setMessageResponse("Media obtenida correctamente");

            return response;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResponseModel getCommentsInRecipe(int id) {
        try {
            ResponseModel response = new ResponseModel();
            response.setData(recipeCommentRepository.getAllByIdReceta(id));
            response.setError(null);
            response.setMessageResponse("Comentarios obtenidos correctamente");

            return response;
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
