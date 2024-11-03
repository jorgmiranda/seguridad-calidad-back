package com.seguridad.seguridad_calidad_back.repository;

import com.seguridad.seguridad_calidad_back.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserModel, Long> {
}
