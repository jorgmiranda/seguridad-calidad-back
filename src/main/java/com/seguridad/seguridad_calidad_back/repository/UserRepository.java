package com.seguridad.seguridad_calidad_back.repository;

import com.seguridad.seguridad_calidad_back.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserModel, Long> {
    UserModel findByCorreo(String correo);
}
