package com.prueba.api;

import com.prueba.api.controller.ClienteController;
import com.prueba.api.model.Cliente;
import com.prueba.api.service.ClienteServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ClienteControllerTest {

    @InjectMocks
    private ClienteController clienteController;  // El controlador a probar

    @Mock
    private ClienteServiceImpl clienteService;  // Servicio simulado

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);  // Inicializar las anotaciones de Mockito
    }

    @Test
    void testGetAllClientesSuccess() {
        // Crear una lista simulada de clientes
        List<Cliente> clientes = Arrays.asList(new Cliente("John" , "alejandro", "Doe", "jimenez", "322332","calle 7b#18","cajica","CC","1070019303"),
        		new Cliente("Hugo" , "sebastian", "lima", "acero", "66522","calle 4b#18","zipaquira","CC","10992923"));
        
        // Definir el comportamiento esperado del servicio
        when(clienteService.getAllClientes()).thenReturn(clientes);

        // Llamar al método del controlador
        ResponseEntity<List<Cliente>> response = clienteController.getAllClientes();

        // Verificar que el estado de la respuesta sea 200 OK
        assertEquals(HttpStatus.OK, response.getStatusCode());

        // Verificar que la respuesta contenga los clientes esperados
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());  // Verifica que haya 2 clientes
    }

    @Test
    void testGetAllClientesNoContent() {
        // Simular una respuesta vacía del servicio (sin clientes)
        when(clienteService.getAllClientes()).thenReturn(Collections.emptyList());

        // Llamar al método del controlador
        ResponseEntity<List<Cliente>> response = clienteController.getAllClientes();

        // Verificar que el estado de la respuesta sea 204 No Content
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

        // Verificar que el cuerpo de la respuesta sea null (sin contenido)
        assertNull(response.getBody());
    }


    @Test
    void testGetAllClientesException() {
        // Simular una excepción en el servicio
        when(clienteService.getAllClientes()).thenThrow(new RuntimeException("Error inesperado"));

        // Llamar al método del controlador
        ResponseEntity<List<Cliente>> response = clienteController.getAllClientes();

        // Verificar que el estado de la respuesta sea 500 Internal Server Error
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());

        // Verificar que el cuerpo de la respuesta sea null (porque hubo un error)
        assertNull(response.getBody());
    }

}


