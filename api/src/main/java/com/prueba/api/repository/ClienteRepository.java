package com.prueba.api.repository;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.prueba.api.model.Cliente;

/*
 * Interface que extiende MongoRepository para interactuar con la base de datos MongoDB.
 * Esta interfaz proporciona métodos personalizados para consultar y manipular la entidad Cliente.
 */
@Repository
public interface ClienteRepository extends MongoRepository<Cliente, String> {

    /**
     * Método para encontrar una lista de clientes basados en su tipo de documento y número de identificación.
     * @param tipodocumento Tipo de documento del cliente (por ejemplo, Cédula, Pasaporte, etc.).
     * @param numerodeidentificacion Número de identificación del cliente.
     * @return Lista de clientes que coinciden con el tipo de documento y número de identificación proporcionados.
     */
    List<Cliente> findByTipoAndNumeroDeIdentificacion(String tipodocumento, String numerodeidentificacion);

    /**
     * Método para encontrar un cliente basado en su número de identificación.
     * @param numerodeidentificacion El número de identificación del cliente.
     * @return El cliente que coincide con el número de identificación, o null si no se encuentra.
     */
    Cliente findByNumeroDeIdentificacion(String numerodeidentificacion);
}
