package com.seguridad.seguridad_calidad_back.core.security;

import com.seguridad.seguridad_calidad_back.core.utils.Constants;
import com.seguridad.seguridad_calidad_back.core.utils.JWTAuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@EnableWebSecurity()
@Configuration
class WebSecurityConfig{

    @Autowired
    JWTAuthorizationFilter jwtAuthorizationFilter;

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests( authz -> authz
                        .requestMatchers(HttpMethod.POST, "/api/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/registrar").permitAll()
//                        .requestMatchers(HttpMethod.POST, Constants.REGISTER_URL).permitAll()
                        //Despues quitar de permitidos
                        // .requestMatchers(HttpMethod.POST,"/recetas").permitAll()
                        // .requestMatchers(HttpMethod.GET,"/ingredientes").permitAll()
                        // .requestMatchers(HttpMethod.GET,"/recetas/usuario/**").permitAll()
                        // .requestMatchers(HttpMethod.GET,"/recetas/**").permitAll()
                        // .requestMatchers(HttpMethod.POST,"/recetas/**").permitAll()
                        // .requestMatchers(HttpMethod.GET,"/recetas/full").permitAll()
                        //cierre
                        .requestMatchers(HttpMethod.POST,"/recetas/filtrar").permitAll()
                        .requestMatchers(HttpMethod.GET,"/recetas/parcial").permitAll()
                        .requestMatchers(HttpMethod.GET,"/recetas/**").permitAll()
                        .requestMatchers(HttpMethod.GET,"/api/usuarios").permitAll()
                        .anyRequest().authenticated())
                .addFilterAfter(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}