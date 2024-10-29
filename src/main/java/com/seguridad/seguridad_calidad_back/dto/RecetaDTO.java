package com.seguridad.seguridad_calidad_back.dto;

public class RecetaDTO {
    private Long id;
    private String nombre;
    private String tipoDeCocina;
    private String dificultadElaboracion;
    private int tiempoCoccion;
    public RecetaDTO() {
    }
    public RecetaDTO(Long id, String nombre, String tipoDeCocina, String dificultadElaboracion, int tiempoCoccion) {
        this.id = id;
        this.nombre = nombre;
        this.tipoDeCocina = tipoDeCocina;
        this.dificultadElaboracion = dificultadElaboracion;
        this.tiempoCoccion = tiempoCoccion;
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
    public String getTipoDeCocina() {
        return tipoDeCocina;
    }
    public void setTipoDeCocina(String tipoDeCocina) {
        this.tipoDeCocina = tipoDeCocina;
    }
    public String getDificultadElaboracion() {
        return dificultadElaboracion;
    }
    public void setDificultadElaboracion(String dificultadElaboracion) {
        this.dificultadElaboracion = dificultadElaboracion;
    }
    public int getTiempoCoccion() {
        return tiempoCoccion;
    }
    public void setTiempoCoccion(int tiempoCoccion) {
        this.tiempoCoccion = tiempoCoccion;
    }
    
    
}
