import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class FormStudentsDataService {

  constructor() { }

  registerStudent(data: any) {
    alert("passou register");
    console.log(data);
  }
    
  pendingStudent(data: any) {  
    alert("passou pending");
    console.log(data);
  }
}
