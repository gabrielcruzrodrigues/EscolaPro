import { HttpClient, HttpResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { environment } from '../../../environments/environment';

interface LoginResponse {
  token: string;
  username: string;
}

@Component({
  selector: 'app-login-page',
  standalone: true,
  imports: [ReactiveFormsModule, HttpClientModule, CommonModule],
  templateUrl: './login-page.component.html',
  styleUrl: './login-page.component.scss'
})
export class LoginPageComponent {

  readonly apiUrl = environment.apiUrl;
  form: FormGroup;
  badCredentialsTextView: boolean = false;

  constructor(private fb: FormBuilder, private http: HttpClient) {
    this.form = this.fb.group({
      username: [''],
      password: ['']
    })
  }

  submitForm() {
    const formData = this.form.value;
      this.http.post<LoginResponse>(`${this.apiUrl}/auth/login`, formData, { observe: 'response' })
        .subscribe({
          next: (response: HttpResponse<LoginResponse>) => {
            if (response.status == 200 && response.body?.token != null) {
              alert("logado!");
            }
          },
          error: (error) => {
            if (error.status === 401) {
              this.badCredentials();
            }
          }
        }) 
  }

  badCredentials() {
    var paragraph = document.getElementById("bad-credentials");
    if (paragraph) {
      paragraph.classList.toggle("hidden");
      paragraph.classList.toggle("visible");
    }
    this.badCredentialsTextView = true;
  }
}
