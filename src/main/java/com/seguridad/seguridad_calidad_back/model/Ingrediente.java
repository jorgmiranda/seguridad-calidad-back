package com.seguridad.seguridad_calidad_back.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "ingredientes")
public class Ingrediente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true, length = 255)
    private String nombre;

    @OneToMany(mappedBy = "ingrediente", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<RecetaIngrediente> recetaIngredientes;

    

    public Ingrediente(Long id, String nombre, Set<RecetaIngrediente> recetaIngredientes) {
        this.id = id;
        this.nombre = nombre;
        this.recetaIngredientes = recetaIngredientes;
    }

    public Ingrediente() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Set<RecetaIngrediente> getRecetaIngredientes() {
        return recetaIngredientes;
    }

    public void setRecetaIngredientes(Set<RecetaIngrediente> recetaIngredientes) {
        this.recetaIngredientes = recetaIngredientes;
    }

    
}
