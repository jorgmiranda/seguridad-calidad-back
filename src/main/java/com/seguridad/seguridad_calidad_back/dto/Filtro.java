package com.seguridad.seguridad_calidad_back.dto;

public class Filtro {
    private String nombre;
    private String tipoCocina;
    private String ingredientes;
    private String paisOrigen;
    private String dificultad;
    public Filtro() {
    }
    public Filtro(String nombre, String tipoCocina, String ingredientes, String paisOrigen, String dificultad) {
        this.nombre = nombre;
        this.tipoCocina = tipoCocina;
        this.ingredientes = ingredientes;
        this.paisOrigen = paisOrigen;
        this.dificultad = dificultad;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getTipoCocina() {
        return tipoCocina;
    }
    public void setTipoCocina(String tipoCocina) {
        this.tipoCocina = tipoCocina;
    }
    public String getIngredientes() {
        return ingredientes;
    }
    public void setIngredientes(String ingredientes) {
        this.ingredientes = ingredientes;
    }
    public String getPaisOrigen() {
        return paisOrigen;
    }
    public void setPaisOrigen(String paisOrigen) {
        this.paisOrigen = paisOrigen;
    }
    public String getDificultad() {
        return dificultad;
    }
    public void setDificultad(String dificultad) {
        this.dificultad = dificultad;
    }

    
}
