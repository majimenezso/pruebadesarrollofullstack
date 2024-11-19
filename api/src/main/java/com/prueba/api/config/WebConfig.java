package com.prueba.api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/*
 * Configuración de Spring MVC para habilitar CORS (Cross-Origin Resource Sharing).
 * Esta clase implementa WebMvcConfigurer para personalizar la configuración de Spring Web.
 * En este caso, estamos configurando las políticas CORS para controlar el acceso a los recursos desde otros dominios.
 */
@Configuration // Marca esta clase como una clase de configuración de Spring
public class WebConfig implements WebMvcConfigurer {

    /*
     * Método para agregar configuraciones de CORS (Cross-Origin Resource Sharing).
     * CORS es un mecanismo que permite a los servidores indicar a los navegadores qué dominios externos
     * pueden acceder a los recursos de un servidor.
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Aplica esta configuración a todas las rutas (/**).
            .allowedOriginPatterns("http://localhost:8090/api") // Permite solicitudes solo desde el origen http://localhost:8090/api
            .allowedMethods("*") // Permite todos los métodos HTTP (GET, POST, PUT, DELETE, etc.)
            .allowedHeaders("Content-Type", "Authorization") // Permite los encabezados Content-Type y Authorization
            .allowCredentials(false) // No se permiten credenciales (cookies, cabeceras de autenticación, etc.)
            .maxAge(3600); // Configura la vida máxima de la configuración CORS a 3600 segundos (1 hora)
    }

}
