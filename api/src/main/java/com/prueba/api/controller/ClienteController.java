package com.prueba.api.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.prueba.api.model.Cliente;
import com.prueba.api.service.ClienteServiceImpl;
import java.util.Collections;

/*
 * Controlador REST para manejar las operaciones CRUD de la entidad Cliente.
 * Este controlador expone los endpoints para crear, leer, actualizar y eliminar clientes.
 * Utiliza los servicios proporcionados por ClienteServiceImpl para interactuar con la base de datos.
 */
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600) // Permite CORS (Cross-Origin Resource Sharing)
@RestController // Define esta clase como un controlador REST
@RequestMapping("/api") // Prefijo de todas las rutas de este controlador
public class ClienteController {

    // Inyección de dependencias: ClienteServiceImpl es utilizado para realizar operaciones CRUD
    @Autowired
    private ClienteServiceImpl service;

    /*
     * Endpoint para obtener todos los clientes.
     * Responde con un código HTTP 200 OK y la lista de clientes si se encuentran disponibles,
     * o un código HTTP 404 Not Found si no hay clientes.
     */
    @GetMapping("/clientes")
    public ResponseEntity<List<Cliente>> getAllClientes() {
        try {
            // Obtiene la lista de todos los clientes desde el servicio
            List<Cliente> clientes = service.getAllClientes();
            
            // Si la lista está vacía, responde con HTTP 204 No Content
            if (clientes == null || clientes.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);  // 204 No Content
            }

            // Si hay clientes, responde con HTTP 200 y la lista de clientes
            return new ResponseEntity<>(clientes, HttpStatus.OK);  // 200 OK
        } catch (Exception e) {
            // Si ocurre una excepción, responde con HTTP 500 (Error interno del servidor)
            System.out.println(e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);  // 500 Internal Server Error
        }
    }

    /*
     * Endpoint para obtener un cliente específico por tipo de documento y número de identificación.
     * Responde con un código HTTP 200 OK y el cliente si se encuentra, o un código HTTP 404 Not Found si no se encuentra.
     */
    @GetMapping("/cliente/{tipodocumento}/{numerodeidentificacion}") // Ruta dinámica para obtener un cliente por tipo y número de identificación
    public ResponseEntity<List<Cliente>> getCliente(
            @PathVariable String tipodocumento, // El tipo de documento es recibido como variable en la URL
            @PathVariable String numerodeidentificacion) { // El número de identificación es recibido como variable en la URL
        try {
            // Obtiene el cliente desde el servicio
            List<Cliente> cliente = service.getCliente(tipodocumento, numerodeidentificacion);
            
            // Si el cliente no existe, responde con HTTP 404
            if (cliente == null || cliente.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            // Si el cliente es encontrado, responde con HTTP 200 OK y el cliente
            return new ResponseEntity<>(cliente, HttpStatus.OK);
        } catch (Exception e) {
            // Si ocurre una excepción, responde con HTTP 500 (Error interno del servidor)
            return new ResponseEntity<>(Collections.emptyList(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
     * Endpoint para crear un nuevo cliente.
     * Recibe un cliente en el cuerpo de la solicitud (RequestBody) y lo guarda en la base de datos.
     * Responde con código HTTP 201 Created si la creación es exitosa, o HTTP 500 si ocurre un error.
     */
    @PostMapping("/cliente") // Ruta para crear un nuevo cliente
    public ResponseEntity<Void> saveCliente(@RequestBody Cliente cliente) {
        try {
            // Llama al servicio para crear el cliente
            service.create(cliente);
            // Responde con HTTP 201 Created si el cliente es creado correctamente
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            // Si ocurre un error, responde con HTTP 500 (Error interno del servidor)
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
     * Endpoint para actualizar un cliente existente.
     * Recibe el número de identificación del cliente y los datos actualizados en el cuerpo de la solicitud.
     * Responde con HTTP 200 OK si la actualización es exitosa, o HTTP 500 si ocurre un error.
     */
    @PutMapping("/cliente/{numerodeidentificacion}") // Ruta para actualizar un cliente por su número de identificación
    public ResponseEntity<Void> updateCliente(
            @PathVariable String numerodeidentificacion, // Número de identificación del cliente a actualizar
            @RequestBody Cliente cliente) { // Datos actualizados del cliente en el cuerpo de la solicitud
        try {
            // Llama al servicio para actualizar el cliente
            service.update(numerodeidentificacion, cliente);
            // Responde con HTTP 200 OK si la actualización es exitosa
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            // Si ocurre un error, responde con HTTP 500 (Error interno del servidor)
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
     * Endpoint para eliminar todos los clientes.
     * Responde con HTTP 204 No Content si la eliminación es exitosa, o HTTP 500 si ocurre un error.
     */
    @DeleteMapping("/cliente") // Ruta para eliminar todos los clientes
    public ResponseEntity<Void> deleteCliente() {
        try {
            // Llama al servicio para eliminar todos los clientes
            service.delete();
            // Responde con HTTP 204 No Content si la eliminación es exitosa
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            // Si ocurre un error, responde con HTTP 500 (Error interno del servidor)
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

