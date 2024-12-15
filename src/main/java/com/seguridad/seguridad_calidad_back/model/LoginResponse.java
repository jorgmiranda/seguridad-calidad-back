package com.seguridad.seguridad_calidad_back.model;

public class LoginResponse {
    private String token;
    private UserModel user;


    public LoginResponse() {
        //    Added empty constructor to create raw LoginResponses
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
