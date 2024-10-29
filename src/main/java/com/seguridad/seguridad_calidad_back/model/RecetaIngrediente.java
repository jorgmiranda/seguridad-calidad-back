package com.seguridad.seguridad_calidad_back.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
// @NamedQueries({
//     @NamedQuery(name = "RecetaIngrediente.findByIdRecetaId", query = "SELECT ri FROM RecetaIngrediente ri WHERE ri.recetaId = :recetaId"),
//     @NamedQuery(name = "RecetaIngrediente.findByIdIngredienteId", query = "SELECT ri FROM RecetaIngrediente ri WHERE ri.ingredienteId = :ingredienteId")
// })
@Table(name = "receta_ingredientes")
public class RecetaIngrediente {
    @EmbeddedId
    @JsonIgnore
    private RecetaIngredienteId id;
    
    @ManyToOne
    @MapsId("recetaId")
    @JoinColumn(name = "receta_id")
    @JsonBackReference
    private Receta receta;
    
    @ManyToOne
    @MapsId("ingredienteId")
    @JoinColumn(name = "ingrediente_id")
    @JsonBackReference
    private Ingrediente ingrediente;

    public RecetaIngrediente() {
    }

    public String getNombreIngrediente() {
        return ingrediente != null ? ingrediente.getNombre() : null;
    }

    public RecetaIngrediente(RecetaIngredienteId id, Receta receta, Ingrediente ingrediente) {
        this.id = id;
        this.receta = receta;
        this.ingrediente = ingrediente;
    }

    public RecetaIngredienteId getId() {
        return id;
    }

    public void setId(RecetaIngredienteId id) {
        this.id = id;
    }

    public Receta getReceta() {
        return receta;
    }

    public void setReceta(Receta receta) {
        this.receta = receta;
    }

    public Ingrediente getIngrediente() {
        return ingrediente;
    }

    public void setIngrediente(Ingrediente ingrediente) {
        this.ingrediente = ingrediente;
    }

    
}
