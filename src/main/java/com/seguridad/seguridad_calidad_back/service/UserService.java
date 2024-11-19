package com.seguridad.seguridad_calidad_back.service;

import com.seguridad.seguridad_calidad_back.model.ResponseModel;
import com.seguridad.seguridad_calidad_back.model.UserModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {
    ResponseModel findByEmail(String correo, String password);

    ResponseModel registerUser(UserModel user);

    List<UserModel> getAllUsers();

    Optional<UserModel> getUserById(Long id);
}
