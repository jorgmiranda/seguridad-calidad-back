package com.seguridad.seguridad_calidad_back.service;

import com.seguridad.seguridad_calidad_back.model.ResponseModel;
import com.seguridad.seguridad_calidad_back.model.UserModel;
import com.seguridad.seguridad_calidad_back.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {
    ResponseModel findByEmail(String correo, String password) throws UsernameNotFoundException;

    ResponseModel registerUser(UserModel user);

    List<UserModel> getAllUsers();
}
