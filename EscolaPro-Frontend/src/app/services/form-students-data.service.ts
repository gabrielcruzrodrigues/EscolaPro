import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
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
  readonly apiStudents = environment.apiStudent;
  readonly apiFile = environment.apiFile;

  constructor(private http: HttpClient, private authService: AuthService) { }

  submitStudentData(action: string, data: any) {
    const headers = this.authService.getHeaders();
    if (action === 'register') {
      return this.http.post(this.urlStudent, data, {headers, observe: 'response'});
    } else {
      return this.http.post(this.urlStudent, data, {headers, observe: 'response'});
    }
  }

  // registerStudent(data: any): Observable<any> {
  //   const headers = this.authService.getHeaders();
  //   return this.http.post(this.urlStudent, data, {headers, observe: 'response'});
  // }
  
  // pendingStudent(data: any): Observable<any>{  
  //   const headers = this.authService.getHeaders();
  //   return this.http.post(this.urlStudent, data, {headers, observe: 'response'});
  // }
  
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
    console.log(data);
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

  disabledStudent(data: any): Observable<any> {
    const headers = this.authService.getHeaders();
    let urlForRequest = this.apiStudents + "/disable/" + data;
    return this.http.delete(urlForRequest, {headers, observe: 'response'})
  }

  deleteStudent(id: string): Observable<any> {
    const headers = this.authService.getHeaders();
    let urlForRequest = this.apiStudents + "/delete/" + id;
    return this.http.delete(urlForRequest, {headers, observe: 'response'});
  }

  findAllActiveStudents(active: boolean, page: number, size: number): Observable<any> {
    const headers = this.authService.getHeaders();
    let urlForRequest = this.apiStudents;
    return this.http.get(`${urlForRequest}/${active}?page=${page}&size=${size}`, {headers, observe: 'response'});
  }

  DownloadFile(studentId: string, type: string): Observable<HttpResponse<Blob>> {
    const headers = this.authService.getHeaders();
    const urlForRequest = this.apiFile + `/get/${studentId}/${type}`;
    return this.http.get(urlForRequest, {
      headers: headers, 
      responseType: 'blob',
      observe: 'response'
    });
  }
}
