import { HttpResponse } from '@angular/common/http';
import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CepService } from 'src/app/services/externalApi/cep-service.service';
import { FormStudentsDataService } from 'src/app/services/form-students-data.service';

@Component({
  selector: 'app-form-student-stage2',
  templateUrl: './form-student-stage2.component.html',
  styleUrls: ['./form-student-stage2.component.scss']
})
export class FormStudentStage2Component implements OnInit{
  @Input() idStudent: string = '';
  atLeastOneRegisteredFamilyMember: boolean = false;
  isFirstFamily: boolean = true;
  nextStageAvaliable: boolean = false;
  showFinancialresponsibleOptions: boolean = true;

  constructor(
    private formStudentService: FormStudentsDataService, 
    private router: Router,
    private studentService: FormStudentsDataService,
    private cepService: CepService) {}

  ngOnInit(): void {
    this.formData.studentId = this.idStudent;
    this.verifyIfFinancialResponsibleIsPresentForShowOptions();
  }

  verifyIfFinancialResponsibleIsPresentForShowOptions() {
    this.studentService.findStudentById(this.idStudent).subscribe({
      next: (response: any) => {
        if (response.body.financialResponsible != null) {
          this.showFinancialresponsibleOptions = false;
        }
      },
      error: (error) => {
        console.log("Error to search financial responsible by student");
      }
    })
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

  saveFamily() {
    this.formStudentService.createFamily(this.formData).subscribe({
      next: (response: HttpResponse<any>) => {
        if (response.status == 201) {
          if (this.formData.financialResponsible == 'TRUE') {
            this.createFinancialResponsible(this.idStudent);
          } else {
            alert("Familiar salvo com sucesso!")
            this.atLeastOneRegisteredFamilyMember = true;
            this.isFirstFamily = false;
            this.nextStageAvaliable = true;
          }
        }
      },
      error: (error) => alert('Erro ao registrar familiar, verifique as informações!')
    });
  }

  addNewFamily() {

    this.formData = {
      studentId: this.idStudent,
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
    };

    this.verifyIfFinancialResponsibleIsPresentForShowOptions();

    this.atLeastOneRegisteredFamilyMember = false;
    this.isFirstFamily = true;
    this.nextStageAvaliable = true;
  }

  nextStage() {
    if (this.showFinancialresponsibleOptions) {
      this.router.navigate(["students/register/financialresponsible/" + this.idStudent]);
    } else {
      this.router.navigate(["students/register/stage3/" + this.idStudent]);
    }
  }

  createFinancialResponsible(data: any) {
    this.modelingObjFinancialResponsibleData(data);
    this.studentService.createFinancialResponsible(this.financialResponsibleData).subscribe({
      next: (response: HttpResponse<any>) => {
        alert("Familiar salvo com sucesso!");
        this.verifyIfFinancialResponsibleIsPresentForShowOptions();
        this.atLeastOneRegisteredFamilyMember = true;
        this.isFirstFamily = false;
        this.nextStageAvaliable = true;
      },
      error: (error) => {
        alert("error ao criar familiar. Verifique os campos!");
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
          } 
        },
        error: (error) => alert("Este Cep não é valido.")
      });
    }
  }
}
