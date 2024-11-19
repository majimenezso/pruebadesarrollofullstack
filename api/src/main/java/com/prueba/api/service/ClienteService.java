package com.prueba.api.service;

import java.util.List;

/*
 * Interfaz que define las operaciones CRUD (Crear, Leer, Actualizar, Eliminar) para la entidad Cliente.
 * Las implementaciones de esta interfaz deben proporcionar la lógica de negocio para manejar los clientes.
 */

public interface ClienteService<T> {

    /**
     * Método para crear un nuevo cliente.
     * @param t El objeto cliente a crear.
     */
    public abstract void create(T t);

    /**
     * Método para actualizar los datos de un cliente existente.
     * @param numerodeidentificacion Número de identificación del cliente a actualizar.
     * @param t El objeto cliente con los nuevos datos.
     */
    public abstract void update(String numerodeidentificacion, T t);

    /**
     * Método para eliminar todos los clientes.
     * En la implementación, este método puede eliminar todos los registros de clientes de la base de datos.
     */
    public abstract void delete();

    /**
     * Método para obtener todos los clientes almacenados en la base de datos.
     * @return Una lista de objetos `Cliente` que contiene todos los clientes.
     */
    public abstract List<T> getAllClientes();

    /**
     * Método para obtener un cliente específico según su tipo de documento y número de identificación.
     * @param tipodocumento El tipo de documento (por ejemplo, Cédula, Pasaporte, etc.).
     * @param numerodeidentificacion El número de identificación del cliente.
     * @return Una lista de objetos `Cliente` que coinciden con el tipo de documento y número de identificación proporcionados.
     */
    public abstract List<T> getCliente(String tipodocumento, String numerodeidentificacion);
}
