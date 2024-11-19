package com.prueba.api.model;

import java.io.Serializable;
import org.springframework.data.mongodb.core.mapping.Document;
import com.mongodb.lang.NonNull;
import lombok.Getter;
import lombok.Setter;

/*
 * Modelo de datos que representa un cliente en la base de datos.
 * La clase está mapeada a la colección "datoscliente" en MongoDB.
 * Se utiliza Lombok para generar los métodos getter y setter automáticamente.
 * 
 * Esta clase implementa la interfaz Serializable para permitir que los objetos Cliente sean serializables.
 */
@Getter
@Setter
@Document(collection = "datoscliente") // Indica que esta clase es un documento de MongoDB
public class Cliente implements Serializable {

    /*
     * Propiedades de la clase Cliente.
     * Estas propiedades corresponden a los atributos de un cliente.
     */
    
    private String primernombre; // Primer nombre del cliente
    private String segundonombre; // Segundo nombre del cliente
    private String primerapellido; // Primer apellido del cliente
    private String segundoapellido; // Segundo apellido del cliente
    private String telefono; // Número de teléfono del cliente
    private String direccion; // Dirección del cliente
    private String ciudadresidencia; // Ciudad de residencia del cliente
    private String tipo; // Tipo de documento del cliente (Cédula, Pasaporte, etc.)
    private String numeroDeIdentificacion; // Número de identificación del cliente (como Cédula, Pasaporte, etc.)

    /*
     * Constructor de la clase Cliente.
     * Este constructor permite crear una instancia de Cliente con todos los atributos.
     */
    public Cliente(String primernombre, String segundonombre, String primerapellido, String segundoapellido, 
                   String telefono, String direccion, String ciudadresidencia, String tipo, String numeroDeIdentificacion) {
        this.primernombre = primernombre;
        this.segundonombre = segundonombre;
        this.primerapellido = primerapellido;
        this.segundoapellido = segundoapellido;
        this.telefono = telefono;
        this.direccion = direccion;
        this.ciudadresidencia = ciudadresidencia;
        this.tipo = tipo;
        this.numeroDeIdentificacion = numeroDeIdentificacion;
    }

    /*
     * Este constructor vacío es necesario para que MongoDB pueda crear instancias de la clase Cliente
     * cuando se consulta desde la base de datos. Lombok generará el constructor vacío automáticamente
     * a través de la anotación @NoArgsConstructor si es necesario.
     */
    public Cliente() {
        // Constructor vacío para MongoDB y otras necesidades
    }

}
