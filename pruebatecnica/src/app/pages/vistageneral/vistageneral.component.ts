// Importación de interfaces y servicios necesarios para la aplicación.
import { Cliente } from './../../../interfaces/Cliente';  // Importa la interfaz Cliente que probablemente define la estructura de los datos de un cliente.
import { Component, OnInit } from '@angular/core';  // Importa los decoradores Component y OnInit de Angular.
import { ServiceService } from '../../../services/service.service';  // Importa el servicio personalizado para manejar las operaciones con datos.
import { Router } from '@angular/router';  // Importa el enrutador de Angular para navegar entre rutas.
import { ReactiveFormsModule, Validators, FormGroup } from '@angular/forms';  // Importa módulos y clases para trabajar con formularios reactivos y validadores.
import { FormsModule } from '@angular/forms';  // Importa el módulo de formularios de Angular.
import { provideHttpClient } from "@angular/common/http";  // Importa el módulo para realizar peticiones HTTP.
import { HttpClient } from '@angular/common/http';  // Importa el cliente HTTP para interactuar con un servidor.
import { CommonModule } from '@angular/common';  // Importa el módulo común de Angular.
import { FormBuilder } from '@angular/forms';  // Importa el FormBuilder para crear formularios reactivos de forma más sencilla.
import Swal from 'sweetalert2';  // Importa la librería SweetAlert2 para mostrar alertas personalizadas.

@Component({
  selector: 'app-vistageneral',  // Define el selector del componente que se utilizará en la plantilla.
  standalone: true,  // Indica que este componente es autónomo y no depende de un módulo Angular específico.
  imports: [ReactiveFormsModule, FormsModule, CommonModule],  // Módulos que se usan en este componente.
  templateUrl: './vistageneral.component.html',  // Ruta del archivo HTML que sirve como plantilla para este componente.
  styleUrl: './vistageneral.component.css'  // Ruta del archivo CSS con estilos específicos para este componente.
})
export class VistageneralComponent implements OnInit {  // Declara el componente y la implementación de OnInit para el ciclo de vida del componente.
  documentForm!: FormGroup;  // Declara un FormGroup para gestionar el formulario reactivo, inicializado posteriormente.

  constructor(
    private router: Router,  // Inyecta el servicio de enrutamiento para realizar navegación.
    private Service: ServiceService,  // Inyecta el servicio que maneja las operaciones relacionadas con los datos del cliente.
    private fb: FormBuilder,  // Inyecta el FormBuilder para crear formularios reactivos.
    private http: HttpClient) {}  // Inyecta el cliente HTTP para realizar peticiones a un servidor.

  ngOnInit() {  // Método de ciclo de vida de Angular que se ejecuta cuando el componente es inicializado.
    // Inicialización del formulario reactivo con validadores para los campos tipoDocumento y numeroDocumento.
    this.documentForm = this.fb.group({
      tipoDocumento: ['', Validators.required],  // Campo tipoDocumento, requerido.
      numeroDocumento: ['', [Validators.required, Validators.minLength(8), Validators.maxLength(11), Validators.pattern(/^\d+$/)]]  // Campo numeroDocumento, con múltiples validaciones.
    });
  }

  // Método para formatear el número de documento en el campo correspondiente.
  formatNumber(value: string) { 
    let formattedValue = value.replace(/\D/g, '');  // Elimina cualquier caracter que no sea un número (en caso de que se ingresen letras o símbolos).
    formattedValue = formattedValue.replace(/\B(?=(\d{3})+(?!\d))/g, ",");  // Añade comas para separar miles.
    this.documentForm.get('numeroDocumento')?.setValue(formattedValue, { emitEvent: false });  // Establece el valor formateado en el formulario, sin emitir un evento para evitar bucles de validación innecesarios.
  }

  // Método para realizar la búsqueda de un cliente mediante los datos del formulario.
  buscar() { 
    if (this.documentForm.valid) {  // Verifica si el formulario es válido.
      const tipoDocumento = this.documentForm.get('tipoDocumento')?.value;  // Obtiene el valor del campo tipoDocumento.
      const numeroDocumento = this.documentForm.get('numeroDocumento')?.value;  // Obtiene el valor del campo numeroDocumento.

      // Llama al servicio para obtener los datos del cliente con los parámetros tipo y número de documento.
      this.Service.getCliente(tipoDocumento, numeroDocumento).subscribe({
        next: (res) => {  // Si la respuesta es exitosa (status 200), ejecuta este bloque.
          for (let item of res) {  // Itera sobre los elementos en la respuesta (probablemente una lista de clientes).
            // Almacena información relevante del cliente en el almacenamiento local del navegador.
            localStorage.setItem('primernombre', item.primernombre); 
            localStorage.setItem('primerapellido', item.primerapellido); 
            this.router.navigate(['/informacionbasica']);  // Navega a la ruta '/informacionbasica' para mostrar más detalles del cliente.
          }
        },
        error: (err: any) => {  // Si ocurre un error durante la petición HTTP.
          console.error('Error:', err);  // Muestra el error en la consola.
          // Muestra una alerta de error utilizando SweetAlert2.
          Swal.fire({ icon: 'error', title: 'Error', text: 'Error al consultar la información suministrada.', footer: 'Por favor, verifique su informacion.' });
        },
      });
    }
  }
}
