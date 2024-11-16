package com.seguridad.seguridad_calidad_back.service.serviceImpl;

import com.seguridad.seguridad_calidad_back.core.exceptions.BadRequestException;
import com.seguridad.seguridad_calidad_back.core.exceptions.EmailAlreadyRegisteredException;
import com.seguridad.seguridad_calidad_back.core.exceptions.InvalidCredentialsException;
import com.seguridad.seguridad_calidad_back.core.exceptions.UserNotFoundException;
import com.seguridad.seguridad_calidad_back.core.utils.JWTAuthtenticationConfig;
import com.seguridad.seguridad_calidad_back.model.LoginResponse;
import com.seguridad.seguridad_calidad_back.model.ResponseModel;
import com.seguridad.seguridad_calidad_back.model.UserModel;
import com.seguridad.seguridad_calidad_back.repository.UserRepository;
import com.seguridad.seguridad_calidad_back.service.UserService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    JWTAuthtenticationConfig jwtAuthtenticationConfig;

    @Autowired
    private UserRepository userRepository;

    public List<UserModel> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<UserModel> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public ResponseModel registerUser(UserModel user) {
        try {

            ArrayList<String> missingFields = new ArrayList<>();

            if (user.getEmail() == null || user.getEmail().isEmpty()) {
                missingFields.add("email");
            }
            if (user.getPassword() == null || user.getPassword().isEmpty()) {
                missingFields.add("password");
            }
            if (user.getNombre() == null || user.getNombre().isEmpty()) {
                missingFields.add("nombre");
            }

            if (!missingFields.isEmpty()) {
                throw new BadRequestException("Faltan los valores o campos para: " + missingFields.toString());
            }

            if (userRepository.findByCorreo(user.getEmail()) != null) {
                throw new EmailAlreadyRegisteredException("Email ya registtrado");
            }

            ResponseModel response = new ResponseModel();
            response.setData(userRepository.save(user));
            response.setError(null);
            response.setMessageResponse("usuario registrado con exito");

            return response;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResponseModel findByEmail(String correo, String password) {
        try {

            ResponseModel response = new ResponseModel();
            LoginResponse resp = new LoginResponse();

            UserModel userFound = userRepository.findByCorreo(correo);

            if (userFound == null) {
                throw new UserNotFoundException("Correo: " + correo +" no se encuentra registrado");
            } else if (userFound.getPassword().equals(password)) {

                resp.setToken(jwtAuthtenticationConfig.getJWTToken(correo));

                response.setData( resp);
                response.setError(null);
                response.setMessageResponse( "Bienvenido!");
            } else if (!password.equals(userFound.getPassword())) {

                throw new InvalidCredentialsException("Credenciales incorrectas!");

            }

            return response;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }
}
