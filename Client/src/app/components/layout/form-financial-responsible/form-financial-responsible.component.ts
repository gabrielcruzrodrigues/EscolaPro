import { Component, Input, OnInit } from '@angular/core';
import { CepService } from 'src/app/services/externalApi/cep-service.service';
import { FormStudentsDataService } from 'src/app/services/form-students-data.service';
import { Router } from '@angular/router';
import { HttpResponse } from '@angular/common/http';

@Component({
  selector: 'app-form-financial-responsible',
  templateUrl: './form-financial-responsible.component.html',
  styleUrls: ['./form-financial-responsible.component.scss']
})
export class FormFinancialResponsibleComponent implements OnInit{
  @Input() idStudent: string = '';

  constructor(
    private cepService: CepService, 
    private studentService: FormStudentsDataService,
    private router: Router) {}

  ngOnInit(): void {
    this.formData.studentId = this.idStudent;
  }

  formData = {
    studentId: '',
    name: '',
    address: '',
    workAddress: '',
    occupation: '',
    neighborhood: '',
    numberHouse: '',
    type: '',
    city: '',
    phone: '',
    email: '',
    state: '',
    cep: '',
    dateOfBirth: '',
    cpf: '',
    identity: '',
    financialResponsible: ''
  }

  sendData() {
    this.studentService.createFinancialResponsible(this.formData).subscribe({
      next: (response: HttpResponse<any>) => {
        this.verifyResponse(response);
      },
      error: (err) => console.log('Erro ao deixar estudante como pendente', err)
    });
  }

  verifyCepStudent(event: Event) {
    const target = event.target as HTMLInputElement;
    const query = target.value;

    if (query.length == 8) {
      alert("buscando cep...");
      this.cepService.verifyCep(query).subscribe({
        next: (response: any) => {
          if (response.cep) {
            this.formData.cep = response.cep;
            this.formData.state = response.state;
            this.formData.city = response.city;
            this.formData.neighborhood = response.neighborhood;
            this.formData.address = response.street;
          } 
        },
        error: (error) => alert("Este Cep não é valido.")
      });
    }
  }

  verifyResponse(data: any) {
    if (data.status == 201) {
      alert("Dados Cadastrados!");
      this.router.navigate(["students/register/stage3/" + this.idStudent]);
    } else {
      alert("Erro ao tentar enviar formulário.");
    }
  }
}
