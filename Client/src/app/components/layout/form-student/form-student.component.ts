import { HttpResponse } from '@angular/common/http';
import { Component, Input } from '@angular/core';
import { Router } from '@angular/router';
import { FormStudentsDataService } from 'src/app/services/form-students-data.service';

enum Shift {
  Matutino = 'matutino',
  Vespertino = 'vespertino',
  Noturno = 'noturno'
};

@Component({
  selector: 'app-form-student',
  templateUrl: './form-student.component.html',
  styleUrls: ['./form-student.component.scss']
})
export class FormStudentComponent {
  
  formData = {
    name: '',
    identity: '',
    cpf: '',
    dateOfBirth: '',
    nationality: '',
    naturalness: '',
    sex: '',
    email: '',
    cep: '',
    address: '',
    phone: '',
    neighborhood: '',
    city: '',
    state: '',
    country: '',
    emailPersonResponsible: '',
    responsible: '',
    father: '',
    mother: '',
    shifts: '',
    situation: ''
    // imageProfile: ''
  }

  constructor(private formServiceData: FormStudentsDataService,  private router: Router) {}

  sendData(action: string) {

    if (action == 'pending') {
      this.formData.situation = 'PENDENTE';
      this.formServiceData.pendingStudent(this.formData).subscribe({
        next: (response: HttpResponse<any>) => 
        this.verifyResponse(response.status),
      
        error: (err) => console.log('Erro ao deixar estudante como pendente', err)
      });

    } else if (action == 'register') {
      this.formData.situation = 'MATRICULADO';
      this.formServiceData.registerStudent(this.formData).subscribe({
        next: (response: HttpResponse<any>) => 
          this.verifyResponse(response.status),

        error: (err) => console.log('Erro ao registrar estudante', err)
      });
    }
  }

  verifyResponse(data: any) {
    // console.log('response: ', data)
    if (data == 201) {
      alert("Aluno criado com sucesso!");
      this.router.navigate(["/students"]);
    } else {
      alert("Erro ao tentar enviar formulário.");
    }
  }
}
