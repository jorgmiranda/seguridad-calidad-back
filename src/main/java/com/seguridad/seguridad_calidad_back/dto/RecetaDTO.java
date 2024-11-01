package com.seguridad.seguridad_calidad_back.dto;

import java.util.Date;

public class RecetaDTO {
    private Long id;
    private String nombre;
    private String tipoDeCocina;
    private String dificultadElaboracion;
    private int tiempoCoccion;
    private String urlImagen;
    private Integer popularidad;
    private Date fechaCreacion;
    
    public RecetaDTO() {
    }
    
    public RecetaDTO(Long id, String nombre, String tipoDeCocina, String dificultadElaboracion, int tiempoCoccion,
            String urlImagen) {
        this.id = id;
        this.nombre = nombre;
        this.tipoDeCocina = tipoDeCocina;
        this.dificultadElaboracion = dificultadElaboracion;
        this.tiempoCoccion = tiempoCoccion;
        this.urlImagen = urlImagen;
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

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    public Integer getPopularidad() {
        return popularidad;
    }

    public void setPopularidad(Integer popularidad) {
        this.popularidad = popularidad;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
    
    
}
