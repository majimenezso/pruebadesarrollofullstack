import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable, OnInit } from '@angular/core';
import { Observable, of } from 'rxjs';
import { tap, delay } from 'rxjs/operators';
import { Router } from '@angular/router';
import { Cliente } from '../interfaces/Cliente';

@Injectable({
  providedIn: 'root'
})
// clase servicio angular sprint boot
export class ServiceService {
  // ruta api spring boot con sus cors
  api = 'http://localhost:8090/api';
  headers = new HttpHeaders({ 'Access-Control-Allow-Origin': 'http://localhost:8090/api',
  'Access-Control-Allow-Credentials': 'true',
  'Content-Type': 'application/json; charset=utf-8',
  'Access-Control-Allow-Headers': 'Content-Type, Authorization' });
  //constructor
  constructor(private http: HttpClient,private router: Router) {}

  // funcion get sesion
  getCliente(tipo: string , numerodeidentificacion : string) : Observable<any> {
    return this.http.get<Cliente>(`${this.api}/cliente/${tipo}/${numerodeidentificacion}`,{ headers: this.headers }
    );
  }

  // funcion read
  getClientes(){
    return this.http.get<Cliente[]>(`${this.api}/clientes`,{ headers: this.headers });
  }

  // funcion create
  saveCliente(cliente: Cliente): Observable<Cliente> {
    return this.http.post<Cliente>(`${this.api}/cliente`, cliente, { headers: this.headers });
  }

  // funcion update
  updateCliente(numerodeidentificacion : string , cliente: Cliente) : Observable<Cliente> {
    return this.http.put<Cliente>(`${this.api}/cliente/${numerodeidentificacion}`,cliente,{ headers: this.headers });
  }

  // funcion delete
  deleteCliente(){
    return this.http.delete(`${this.api}/cliente`,{ headers: this.headers });
  }
}
