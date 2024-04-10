import { HttpResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { CepService } from 'src/app/services/externalApi/cep-service.service';
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

  constructor(
    private formServiceData: FormStudentsDataService,  
    private router: Router,
    private cepService: CepService) {}

  sendData(action: string) {

    if (action == 'pending') {
      this.formData.situation = 'PENDENTE';
      this.formServiceData.pendingStudent(this.formData).subscribe({
        next: (response: HttpResponse<any>) => 
        this.verifyResponse(response),
      
        error: (err) => console.log('Erro ao deixar estudante como pendente', err)
      });

    } else if (action == 'register') {
      this.formData.situation = 'MATRICULADO';
      this.formServiceData.registerStudent(this.formData).subscribe({
        next: (response: HttpResponse<any>) => 
          this.verifyResponse(response),

        error: (err) => console.log('Erro ao registrar estudante', err)
      });
    }
  }

  verifyCepStudent(event: Event) {
    const target = event.target as HTMLInputElement;
    const query = target.value;

    if (query.length == 8) {
      this.cepService.verifyCep(query).subscribe({
        next: (response: any) => {
          if (response.cep) {
            console.log(response.cep)
            this.formData.cep = response.cep;
            this.formData.state = response.state;
            this.formData.city = response.city;
            this.formData.neighborhood = response.neighborhood;
            this.formData.address = response.street;
            this.formData.country = 'Brasil';
          } 
        },
        error: (error) => alert("Este Cep não é valido.")
      });
    }
  }

  verifyResponse(data: any) {
    // console.log('response: ', data)
    if (data.status == 201) {
      alert("Dados Cadastrados!");
      this.router.navigate(["students/register/stage2/" + data.body.id]);
    } else {
      alert("Erro ao tentar enviar formulário.");
    }
  }
}
