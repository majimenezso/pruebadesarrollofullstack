// Importación de los módulos y servicios necesarios para la aplicación.
import { Component, OnInit } from '@angular/core';  // Importa los decoradores Component y OnInit de Angular.
import { HttpClient } from '@angular/common/http';  // Importa el cliente HTTP para realizar peticiones a un servidor.
import { Cliente } from './../../../interfaces/Cliente';  // Importa la interfaz Cliente, que define la estructura de los datos de un cliente.
import { ServiceService } from '../../../services/service.service';  // Importa el servicio para gestionar las operaciones relacionadas con los datos de cliente.
import { Router } from '@angular/router';  // Importa el servicio de enrutamiento para navegar entre rutas.
import { ReactiveFormsModule, Validators, FormGroup } from '@angular/forms';  // Importa módulos para trabajar con formularios reactivos y validadores.
import { FormsModule } from '@angular/forms';  // Importa el módulo de formularios de Angular.
import { CommonModule } from '@angular/common';  // Importa el módulo común de Angular, que proporciona funcionalidad común a todos los módulos de Angular.

@Component({
  selector: 'app-informacionbasica',  // Define el selector del componente que se utilizará en la plantilla.
  standalone: true,  // Indica que este componente es autónomo, es decir, no depende de un módulo Angular específico.
  imports: [ReactiveFormsModule, FormsModule, CommonModule],  // Módulos que se usarán en este componente.
  templateUrl: './informacionbasica.component.html',  // Ruta del archivo HTML que sirve como plantilla para este componente.
  styleUrls: ['./informacionbasica.component.css']  // Ruta del archivo CSS con estilos específicos para este componente.
})
export class InformacionbasicaComponent implements OnInit {  // Declara el componente y la implementación de OnInit para el ciclo de vida del componente.

  // Definición de un objeto `Client` de tipo `Cliente`. Este objeto representa los datos del cliente que se mostrarán en la interfaz.
  Client: Cliente = {
    primernombre: '',  // Primer nombre del cliente.
    segundonombre: '',  // Segundo nombre del cliente.
    primerapellido: '',  // Primer apellido del cliente.
    segundoapellido: '',  // Segundo apellido del cliente.
    telefono: '',  // Teléfono del cliente.
    direccion: '',  // Dirección del cliente.
    ciudadresidencia: '',  // Ciudad de residencia del cliente.
  };

  // Constructor que inyecta dependencias necesarias (enrutador, servicio y cliente HTTP).
  constructor(
    private router: Router,  // Inyecta el servicio de enrutamiento para manejar la navegación entre rutas.
    private Service: ServiceService,  // Inyecta el servicio que maneja la interacción con la API o servicios backend.
    private http: HttpClient) { }  // Inyecta el cliente HTTP para realizar peticiones HTTP.

  ngOnInit() {  // Método del ciclo de vida OnInit que se ejecuta cuando el componente se inicializa.
    // Recupera los datos del cliente almacenados en el LocalStorage (probablemente guardados por otro componente, como el anterior).
    const primernombre = localStorage.getItem('primernombre');  // Recupera el primer nombre del cliente desde el almacenamiento local.
    const primerapellido = localStorage.getItem('primerapellido');  // Recupera el primer apellido del cliente desde el almacenamiento local.
   
    // Asigna los valores recuperados a las propiedades del objeto `Client`.
    // Si los valores no están presentes en LocalStorage (es decir, son null), se asigna una cadena vacía como valor por defecto.
    this.Client.primernombre = primernombre ?? '';  
    this.Client.primerapellido = primerapellido ?? '';

    // Muestra los valores recuperados en la consola para depuración.
    console.log(primernombre);
  }

  // Método para navegar de vuelta a la página de inicio (probablemente se refiere a la ruta raíz).
  goBack() { 
    this.router.navigate(['']);  // Navega a la ruta raíz ('') que corresponde al componente inicial de la aplicación.
  }
}
