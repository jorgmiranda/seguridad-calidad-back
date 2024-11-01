package com.seguridad.seguridad_calidad_back.specifications;

import org.springframework.data.jpa.domain.Specification;

import com.seguridad.seguridad_calidad_back.model.Ingrediente;
import com.seguridad.seguridad_calidad_back.model.Receta;
import com.seguridad.seguridad_calidad_back.model.RecetaIngrediente;

import jakarta.persistence.criteria.Join;

public class RecetaSpecifications {
    public static Specification<Receta> nombreContains(String nombre) {
        return (root, query, builder) -> 
            (nombre != null && !nombre.isEmpty()) ? builder.like(root.get("nombre"), "%" + nombre + "%") : null;
    }

    public static Specification<Receta> paisOrigenEquals(String pais) {
        return (root, query, builder) -> 
            (pais != null && !pais.isEmpty()) ? builder.equal(root.get("paisDeOrigen"), pais) : null;
    }

    public static Specification<Receta> dificultadEquals(String dificultad) {
        return (root, query, builder) -> 
            (dificultad != null && !dificultad.isEmpty()) ? builder.equal(root.get("dificultadElaboracion"), dificultad) : null;
    }

    public static Specification<Receta> tipoEquals(String tipo) {
        return (root, query, builder) -> 
            (tipo != null && !tipo.isEmpty()) ? builder.equal(root.get("tipoDeCocina"), tipo) : null;
    }

    public static Specification<Receta> ingredienteContains(String nombreIngrediente) {
        return (root, query, criteriaBuilder) -> {
            // Join con la tabla intermedia
            Join<Receta, RecetaIngrediente> recetaIngredientes = root.join("recetaIngredientes");
            Join<RecetaIngrediente, Ingrediente> ingrediente = recetaIngredientes.join("ingrediente");
            return criteriaBuilder.like(ingrediente.get("nombre"), "%" + nombreIngrediente + "%");
        };
    }
    
}
