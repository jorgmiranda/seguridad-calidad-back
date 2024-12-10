package com.seguridad.seguridad_calidad_back.model;

import jakarta.persistence.*;

@Entity
@Table(name = "comentario_receta")
public class RecipeComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private int idReceta;

    @Column
    private Long id_usuario;

    @Column
    private String comentario;

    private boolean esPublico;
    private int calificacion;

    public RecipeComment() {
    }

    public int getIdReceta() {
        return idReceta;
    }

    public void setIdReceta(int idReceta) {
        this.idReceta = idReceta;
    }

    public boolean isEsPublico() {
        return esPublico;
    }

    public void setEsPublico(boolean esPublico) {
        this.esPublico = esPublico;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
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

    public Long getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Long id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
