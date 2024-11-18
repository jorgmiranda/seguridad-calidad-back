package com.seguridad.seguridad_calidad_back.dto;

public class IngredientesDTO {
    private Long id;
    private String nombreIngrediente;

    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNombreIngrediente() {
        return nombreIngrediente;
    }
    public void setNombreIngrediente(String nombreIngrediente) {
        this.nombreIngrediente = nombreIngrediente;
    }
}
