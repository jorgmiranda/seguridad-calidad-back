package com.seguridad.seguridad_calidad_back.model;



import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "recetas")
public class Receta {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 255)
    private String nombre;
    
    @Column(name = "tipoDeCocina", nullable = false, length = 100)
    private String tipoDeCocina;
    
    @Column(name = "paisDeOrigen", nullable = false, length = 100)
    private String paisDeOrigen;
    
    @Column(name = "dificultadElaboracion", nullable = false, length = 50)
    private String dificultadElaboracion;
    
    @Column(name = "instruccionesPreparacion", nullable = false)
    private String instruccionesPreparacion;
    
    @Column(name = "tiempoCoccion", nullable = false, length = 50)
    private String tiempoCoccion;
    
    @Column(name = "urlImagen", nullable = false, length = 255)
    private String urlImagen;
    
    @Column(name = "fechaCreacion", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;

    @Column(nullable = false)
    private Integer popularidad;

    @OneToMany(mappedBy = "receta", cascade = CascadeType.ALL)
    private Set<RecetaIngrediente> recetaIngredientes;

    public Receta() {
    }

    public Receta(Long id, String nombre, String tipoDeCocina, String paisDeOrigen, String dificultadElaboracion,
            String instruccionesPreparacion, String tiempoCoccion, String urlImagen, Date fechaCreacion,
            Integer popularidad, Set<RecetaIngrediente> recetaIngredientes) {
        this.id = id;
        this.nombre = nombre;
        this.tipoDeCocina = tipoDeCocina;
        this.paisDeOrigen = paisDeOrigen;
        this.dificultadElaboracion = dificultadElaboracion;
        this.instruccionesPreparacion = instruccionesPreparacion;
        this.tiempoCoccion = tiempoCoccion;
        this.urlImagen = urlImagen;
        this.fechaCreacion = fechaCreacion;
        this.popularidad = popularidad;
        this.recetaIngredientes = recetaIngredientes;
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

    public String getPaisDeOrigen() {
        return paisDeOrigen;
    }

    public void setPaisDeOrigen(String paisDeOrigen) {
        this.paisDeOrigen = paisDeOrigen;
    }

    public String getDificultadElaboracion() {
        return dificultadElaboracion;
    }

    public void setDificultadElaboracion(String dificultadElaboracion) {
        this.dificultadElaboracion = dificultadElaboracion;
    }

    public String getInstruccionesPreparacion() {
        return instruccionesPreparacion;
    }

    public void setInstruccionesPreparacion(String instruccionesPreparacion) {
        this.instruccionesPreparacion = instruccionesPreparacion;
    }

    public String getTiempoCoccion() {
        return tiempoCoccion;
    }

    public void setTiempoCoccion(String tiempoCoccion) {
        this.tiempoCoccion = tiempoCoccion;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Integer getPopularidad() {
        return popularidad;
    }

    public void setPopularidad(Integer popularidad) {
        this.popularidad = popularidad;
    }

    public Set<RecetaIngrediente> getRecetaIngredientes() {
        return recetaIngredientes;
    }

    public void setRecetaIngredientes(Set<RecetaIngrediente> recetaIngredientes) {
        this.recetaIngredientes = recetaIngredientes;
    }

    
}