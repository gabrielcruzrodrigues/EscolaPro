import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { environment } from 'src/environment/environment';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { AuthService } from '../services/auth.service';

interface LoginResponse {
  token: string;
  username: string;
}

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.scss']
})
export class LoginPageComponent {

  readonly apiUrl = environment.apiUrl;
  form: FormGroup;
  badCredentialsTextView: boolean = false;

  constructor(
      private fb: FormBuilder, 
      private http: HttpClient, 
      private router: Router,
      private authService: AuthService) {
    this.form = this.fb.group({
      username: [''],
      password: ['']
    })
  }

  submitForm() {
    const formData = this.form.value;
      this.http.post<LoginResponse>(`${this.apiUrl}/auth/login`, formData, { observe: 'response' , withCredentials: true })
        .subscribe({
          next: (response: HttpResponse<LoginResponse>) => {

            if (response.status == 200) {
              this.authService.configureAuthenticationTokenForRequests(response.body?.token);
              this.router.navigate(["/students"]);
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
    this.badCredentialsTextView = true;
  }

}
