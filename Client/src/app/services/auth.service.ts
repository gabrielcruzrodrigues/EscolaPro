import { HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor() { }

  configureAuthenticationTokenForRequests(token: any) {
    if (token) {
      localStorage.setItem('token', token);
    } else {
      alert("erro ao tentar configurar o acesso! :AuthService")
    }
  }

  getHeaders() {
    const token = localStorage.getItem('token');
    if (token) {
      return new HttpHeaders().set('Authorization', `Bearer ${token}`);
    } else {
      return new HttpHeaders();
    }
  }
}
