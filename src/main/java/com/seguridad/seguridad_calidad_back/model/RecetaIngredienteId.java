package com.seguridad.seguridad_calidad_back.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class RecetaIngredienteId implements Serializable {
    
    private Long recetaId;
    private Long ingredienteId;

    

    public RecetaIngredienteId() {
    }



    public RecetaIngredienteId(Long recetaId, Long ingredienteId) {
        this.recetaId = recetaId;
        this.ingredienteId = ingredienteId;
    }



    public Long getRecetaId() {
        return recetaId;
    }



    public void setRecetaId(Long recetaId) {
        this.recetaId = recetaId;
    }



    public Long getIngredienteId() {
        return ingredienteId;
    }



    public void setIngredienteId(Long ingredienteId) {
        this.ingredienteId = ingredienteId;
    }

       // Implementación de equals
       @Override
       public boolean equals(Object o) {
           if (this == o) return true;
           if (!(o instanceof RecetaIngredienteId)) return false;
           RecetaIngredienteId that = (RecetaIngredienteId) o;
           return recetaId.equals(that.recetaId) && ingredienteId.equals(that.ingredienteId);
       }
   
       // Implementación de hashCode
       @Override
       public int hashCode() {
           int result = recetaId.hashCode();
           result = 31 * result + ingredienteId.hashCode();
           return result;
       }
}