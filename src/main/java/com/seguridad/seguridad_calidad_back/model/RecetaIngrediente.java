package com.seguridad.seguridad_calidad_back.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "receta_ingredientes")
public class RecetaIngrediente {
    @EmbeddedId
    private RecetaIngredienteId id;
    
    @ManyToOne
    @MapsId("recetaId")
    @JoinColumn(name = "receta_id")
    private Receta receta;
    
    @ManyToOne
    @MapsId("ingredienteId")
    @JoinColumn(name = "ingrediente_id")
    private Ingrediente ingrediente;

    public RecetaIngrediente() {
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
