import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CepService {
  urlBrasilApi: string = 'https://brasilapi.com.br/api/cep/v1/';

  constructor(private http: HttpClient) { }

  verifyCep(data: any): Observable<any> {
    return this.http.get(this.urlBrasilApi + data);
  }
}
