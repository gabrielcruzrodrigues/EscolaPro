import { Component } from '@angular/core';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { response } from 'express';

@Component({
  selector: 'app-login-page',
  standalone: true,
  imports: [],
  templateUrl: './login-page.component.html',
  styleUrl: './login-page.component.scss'
})
export class LoginPageComponent {

  formData: any = {}

  constructor(private http: HttpClient) {}

  submitForm() {
    this.http.post("http://localhost:8080/login", this.formData)
    .subscribe(
      response => {
        console.log('Resposta do servidor:', response)
      },
      error => {
        console.error("Erro ao enviar formulário: ", error);
      }
    );
  }
}
