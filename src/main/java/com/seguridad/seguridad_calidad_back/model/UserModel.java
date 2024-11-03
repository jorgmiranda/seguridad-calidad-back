package com.seguridad.seguridad_calidad_back.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull(message="Debe ingresar Email")
    private String correo;

    @NotNull(message = "Debe ingresar contraseña")
    @Size(min = 6, max = 12, message = "La contraseña debe tener entre 6 y 12 caracteres")
    private String contrasena;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return correo;
    }

    public void setEmail( String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return contrasena;
    }

    public void setPassword(String contrasena) {
        this.contrasena = contrasena;
    }
}