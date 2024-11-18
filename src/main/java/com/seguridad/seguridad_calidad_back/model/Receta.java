package com.seguridad.seguridad_calidad_back.model;



import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.util.Date;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "recetas")
public class Receta {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 255)
    private String nombre;
    
    @Column(name = "tipo_de_cocina", nullable = false, length = 100)
    private String tipoDeCocina;
    
    @Column(name = "pais_de_origen", nullable = false, length = 100)
    private String paisDeOrigen;
    
    @Column(name = "dificultad_elaboracion", nullable = false, length = 50)
    private String dificultadElaboracion;
    
    @Column(name = "instrucciones_preparacion", nullable = false)
    private String instruccionesPreparacion;
    
    @Column(name = "tiempo_coccion", nullable = false, length = 50)
    private Integer tiempoCoccion;
    
    @Column(name = "url_imagen", nullable = false, length = 255)
    private String urlImagen;

    @Column(name = "url_video", nullable = true, length = 255)
    private String urlVideo;
    
    @Column(name = "fecha_creacion", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;

    @Column(nullable = false)
    private Integer popularidad;

    @OneToMany(mappedBy = "receta", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<RecetaIngrediente> recetaIngredientes;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    @JsonBackReference
    private UserModel usuario;

    public Receta() {
    }

    public Receta(Long id, String nombre, String tipoDeCocina, String paisDeOrigen, String dificultadElaboracion,
            String instruccionesPreparacion, Integer tiempoCoccion, String urlImagen, Date fechaCreacion,
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

    public Integer getTiempoCoccion() {
        return tiempoCoccion;
    }

    public void setTiempoCoccion(Integer tiempoCoccion) {
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

    public UserModel getUsuario() {
        return usuario;
    }

    public void setUsuario(UserModel usuario) {
        this.usuario = usuario;
    }

    public String getUrlVideo() {
        return urlVideo;
    }

    public void setUrlVideo(String urlVideo) {
        this.urlVideo = urlVideo;
    }

    
}