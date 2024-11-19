package com.seguridad.seguridad_calidad_back.controller;

import com.seguridad.seguridad_calidad_back.model.ResponseModel;
import com.seguridad.seguridad_calidad_back.model.UserModel;
import com.seguridad.seguridad_calidad_back.service.UserService;
import com.seguridad.seguridad_calidad_back.core.utils.JWTAuthtenticationConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api")
@RestController
public class LoginController {



    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseModel login(@RequestParam("correo") String correo, @RequestParam("password") String password) {
        try {
            return userService.findByEmail(correo, password);

        }catch (Exception e) {
            return new ResponseModel("Error al intentar login", null, e.getMessage());
        }
    }

    @GetMapping("/usuarios")
    public ResponseEntity<List<UserModel>> getUsuarios() {
        final List<UserModel> users = userService.getAllUsers();

        return ResponseEntity.ok(users);
    }

    @PostMapping("/registrar")
    public ResponseModel registerUser(@RequestBody UserModel user) {
        // podríamos considerar encriptar la pass, pero no va a ocurrir ahora xD
        try {
            return userService.registerUser(user);

        } catch (Exception e) {
            return new ResponseModel("Error al intentar Registro", null, e.getMessage());
        }

    }

}