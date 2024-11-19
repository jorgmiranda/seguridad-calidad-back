package com.seguridad.seguridad_calidad_back.model;

import jakarta.persistence.*;

@Entity
@Table(name = "comentario_receta")
public class RecipeComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private int id_receta;

    @Column
    private int id_usuario;

    @Column
    private String comentario;

    public RecipeComment() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getId_receta() {
        return id_receta;
    }

    public void setId_receta(int id_receta) {
        this.id_receta = id_receta;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
