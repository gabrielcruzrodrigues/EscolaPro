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
    situation: '',
    numberHouse: '',
    financialResponsible: ''
    // imageProfile: ''
  }

  financialResponsibleData = {
    name: '',
    email: '',
    dateOfBirth: '',
    cpf: '',
    identity: '',
    address: '',
    workAddress: '',
    occupation: '',
    neighborhood: '',
    numberHouse: '',
    city: '',
    phone: '',
    state: '',
    cep: '',
    type: '',
    studentId: ''
  }

  constructor(
    private studentService: FormStudentsDataService,  
    private router: Router,
    private cepService: CepService) {}

  sendData(action: string) {

    if (action == 'pending') {
      this.formData.situation = 'PENDENTE';
      this.studentService.pendingStudent(this.formData).subscribe({
        next: (response: HttpResponse<any>) => 
        this.verifyResponse(response),
      
        error: (err) => console.log('Erro ao deixar estudante como pendente', err)
      });

    } else if (action == 'register') {
      this.formData.situation = 'MATRICULADO';
      this.studentService.registerStudent(this.formData).subscribe({
        next: (response: HttpResponse<any>) => {
          this.verifyResponse(response)
        },
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
            alert("buscando cep...");
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
    if (data.status == 201) {
      if (this.formData.financialResponsible == 'TRUE') {
        this.createFinancialResponsible(data.body.id);    
      }
      alert("Dados Cadastrados!");
      this.router.navigate(["students/register/stage2/" + data.body.id]);
    } else {
      alert("Erro ao tentar enviar formulário.");
    }
  }

  createFinancialResponsible(data: any) {
    this.modelingObjFinancialResponsibleData(data);
    this.studentService.createFinancialResponsible(this.financialResponsibleData).subscribe({
      next: (response: HttpResponse<any>) => {
        console.log("Financial responsible created with success.");
      },
      error: (error) => {
        console.log("Financial Responsible error", error.message);
      }
    })
  }

  modelingObjFinancialResponsibleData(idStudent: any) {
    this.financialResponsibleData.name = this.formData.name;
    this.financialResponsibleData.email = this.formData.email;
    this.financialResponsibleData.dateOfBirth = this.formData.dateOfBirth;
    this.financialResponsibleData.address = this.formData.address;
    this.financialResponsibleData.workAddress = this.formData.address;
    this.financialResponsibleData.occupation = '';
    this.financialResponsibleData.neighborhood = this.formData.neighborhood;
    this.financialResponsibleData.numberHouse = this.formData.numberHouse;
    this.financialResponsibleData.city = this.formData.city;
    this.financialResponsibleData.phone = this.formData.phone;
    this.financialResponsibleData.state = this.formData.state;
    this.financialResponsibleData.cep = this.formData.cep;
    this.financialResponsibleData.type = 'STUDENT';
    this.financialResponsibleData.studentId = idStudent;
    this.financialResponsibleData.cpf = this.formData.cpf;
    this.financialResponsibleData.identity = this.formData.identity;
  }
}
