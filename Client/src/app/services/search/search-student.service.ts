import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AuthService } from '../auth.service';
import { Observable } from 'rxjs';
import { environment } from 'src/environment/environment';

@Injectable({
  providedIn: 'root'
})
export class SearchStudentService {

  readonly apiUrl = environment.apiSearchStudent;

  constructor(private http: HttpClient, private authService: AuthService) { }

  searchStudent(data: any): Observable<any> {
    const headers = this.authService.getHeaders();
    let urlForRequest = this.apiUrl + data; 
    return this.http.get(urlForRequest, {headers, observe: 'response'});
  }

  
}
