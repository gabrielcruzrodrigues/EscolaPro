import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environment/environment';
import { Observable } from 'rxjs';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class FormStudentsDataService {

  readonly apiUrl = environment.apiUrl;
  urlStudent = this.apiUrl + "/student";
  urlUpdate = environment.apiUpdate;
  urlFamily = environment.apiFamily;
  urlFixedhealth = environment.apiFixedhealth;
  urlFinancialResponsible = environment.apiFinancialResponsible;
  readonly urlSearchByName = environment.apiSearchStudentByName;
  readonly apiFindStudentById = environment.apiFindStudentById;

  constructor(private http: HttpClient, private authService: AuthService) { }

  registerStudent(data: any): Observable<any> {
    const headers = this.authService.getHeaders();
    return this.http.post(this.urlStudent, data, {headers, observe: 'response'});
  }
  
  pendingStudent(data: any): Observable<any>{  
    const headers = this.authService.getHeaders();
    return this.http.post(this.urlStudent, data, {headers, observe: 'response'});
  }
  
  updateStudent(data: any): Observable<any> {
    const headers = this.authService.getHeaders();
    return this.http.put(this.urlUpdate, data, {headers, observe: 'response'});
  }

  createFamily(data: any): Observable<any> {
    const headers = this.authService.getHeaders();
    return this.http.post(this.urlFamily, data, {headers, observe: 'response'});
  }

  createFixedhealth(data: any): Observable<any> {
    const headers = this.authService.getHeaders();
    return this.http.post(this.urlFixedhealth, data, {headers, observe: 'response'});
  }

  createFinancialResponsible(data: any): Observable<any> {
    const headers = this.authService.getHeaders();
    return this.http.post(this.urlFinancialResponsible, data, {headers, observe: 'response'});
  }

  searchStudentByName(data: any): Observable<any> {
    const headers = this.authService.getHeaders();
    let urlForRequest = this.urlSearchByName + data; 
    return this.http.get(urlForRequest, {headers, observe: 'response'});
  }

  findStudentById(data: any): Observable<any> {
    const headers = this.authService.getHeaders();
    let urlForRequest = this.apiFindStudentById + data;
    return this.http.get(urlForRequest, {headers, observe: 'response'})
  }
}
