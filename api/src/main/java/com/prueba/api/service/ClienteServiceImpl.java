package com.prueba.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import com.prueba.api.model.Cliente;
import com.prueba.api.repository.ClienteRepository;

/*
 * Clase Service que implementa operaciones CRUD para la entidad Cliente.
 * Esta clase maneja las operaciones de creación, actualización, eliminación y obtención de clientes.
 */

@Service
public class ClienteServiceImpl implements ClienteService<Cliente> {

    // Inyección de dependencia para el repositorio de Cliente
    @Autowired
    ClienteRepository repository;
    
    /**
     * Método para crear un nuevo cliente.
     * @param cliente Cliente a ser creado.
     */
    @Override
    public void create(Cliente cliente) {
        // Guardamos el cliente en la base de datos usando el repositorio
        repository.save(cliente);
    }

    /**
     * Método para actualizar los datos de un cliente existente.
     * @param numeroDeIdentificacion Número de identificación del cliente a actualizar.
     * @param cliente Datos del cliente a actualizar.
     */
    @Override
    public void update(String numeroDeIdentificacion, Cliente cliente) {
        try {
            // Buscamos el cliente existente por su número de identificación
            Cliente existingCliente = repository.findByNumeroDeIdentificacion(numeroDeIdentificacion);

            // Si el cliente existe, actualizamos sus datos
            if (existingCliente != null) {
                existingCliente.setPrimernombre(cliente.getPrimernombre());
                existingCliente.setSegundonombre(cliente.getSegundonombre());
                existingCliente.setPrimerapellido(cliente.getPrimerapellido());
                existingCliente.setSegundoapellido(cliente.getSegundoapellido());
                existingCliente.setTelefono(cliente.getTelefono());
                existingCliente.setDireccion(cliente.getDireccion());
                existingCliente.setCiudadresidencia(cliente.getCiudadresidencia());
                existingCliente.setTipo(cliente.getTipo());
                existingCliente.setNumeroDeIdentificacion(cliente.getNumeroDeIdentificacion());
                
                // Guardamos el cliente actualizado en la base de datos
                repository.save(existingCliente);
            } else {
                // Si el cliente no existe, lanzamos una excepción
                throw new EntityNotFoundException("Cliente con número de identificación: " 
                        + numeroDeIdentificacion + " no ha sido encontrado");
            }
        } catch (Exception e) {
            // Capturamos cualquier error que ocurra y lo imprimimos
            e.printStackTrace();
        }
    }

    /**
     * Método para eliminar todos los clientes de la base de datos.
     */
    @Override
    public void delete() {
        // Eliminamos todos los clientes de la base de datos
        repository.deleteAll();
    }

    /**
     * Método para obtener todos los clientes de la base de datos.
     * @return Lista de todos los clientes.
     */
    @Override
    public List<Cliente> getAllClientes() {
        // Retornamos todos los clientes almacenados en la base de datos
        return repository.findAll();
    }

    /**
     * Método para obtener un cliente específico por tipo de documento y número de identificación.
     * @param tipoDocumento Tipo de documento del cliente.
     * @param numeroDeIdentificacion Número de identificación del cliente.
     * @return Lista de clientes que coinciden con los parámetros proporcionados.
     */
    @Override
    public List<Cliente> getCliente(String tipoDocumento, String numeroDeIdentificacion) {
        // Buscamos clientes por tipo de documento y número de identificación
        return repository.findByTipoAndNumeroDeIdentificacion(tipoDocumento, numeroDeIdentificacion);
    }
}
