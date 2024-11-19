package com.seguridad.seguridad_calidad_back.model;

import jakarta.persistence.*;

@Entity
@Table(name = "calificacion_receta")
public class RecipeCalification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private int idReceta;

    @Column
    private int id_usuario;

    @Column
    private int calificacion;

    public RecipeCalification() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getId_receta() {
        return idReceta;
    }

    public void setId_receta(int id_receta) {
        this.idReceta = id_receta;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }
}
