package com.seguridad.seguridad_calidad_back.service;

import com.seguridad.seguridad_calidad_back.model.UserModel;
import com.seguridad.seguridad_calidad_back.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserModel> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<UserModel> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public UserModel loadUserByUsername(String username) {

        UserModel user = new UserModel();

        user.setEmail("pe.falfan@duocuc.cl");
        user.setPassword("123456");
        return user;
    }

    public UserModel saveUser(UserModel user) {
        return userRepository.save(user);
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }


}
