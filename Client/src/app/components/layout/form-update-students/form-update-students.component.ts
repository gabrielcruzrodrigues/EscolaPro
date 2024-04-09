import { HttpResponse } from '@angular/common/http';
import { Component, Input, OnInit } from '@angular/core';
import { FormStudentsDataService } from 'src/app/services/form-students-data.service';
import { SearchStudentService } from 'src/app/services/search/search-student.service';


enum Shift {
  Matutino = 'matutino',
  Vespertino = 'vespertino',
  Noturno = 'noturno'
};

@Component({
  selector: 'app-form-update-student',
  templateUrl: './form-update-students.component.html',
  styleUrls: ['./form-update-students.component.scss']
})
export class FormUpdateStudentsComponent implements OnInit{
  @Input() idStudent: string = '';

  name = '';
  identity = '';
  cpf = '';
  dateOfBirth = '';
  nationality = '';
  naturalness = '';
  sex = '';
  email = '';
  cep = '';
  address = '';
  phone = '';
  neighborhood = '';
  city = '';
  state = '';
  country = '';
  emailPersonResponsible = '';
  responsible = '';
  father = '';
  mother = '';
  shifts = '';
  situation = '';

  formData = {
    id: '',
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
    shifts: ''
    // imageProfile: ''
  }

  constructor(private searchService: SearchStudentService, private studentService: FormStudentsDataService) {}

  ngOnInit(): void {
    this.searchStudent(this.idStudent);
  }


  searchStudent(data: any) {
    const id = data;
    this.searchService.findStudentById(id).subscribe({
      next: (response: HttpResponse<any>) => {
        console.log(response);
        this.fillOutForm(response.body);
      },
      error: (error) => {
        console.log(error);
      }
    })
  }

  fillOutForm(data: any) {
    this.name = data.name;
    this.identity = data.identity;
    this.cpf = data.cpf;
    this.dateOfBirth = data.dateOfBirth;
    this.nationality = data.nationality;
    this.naturalness = data.naturalness;
    this.sex = data.sex;
    this.email = data.email;
    this.cep = data.cep;
    this.address = data.address;
    this.phone = data.phone;
    this.neighborhood = data.neighborhood;
    this.city = data.city;
    this.state = data.state;
    this.country = data.country;
    this.emailPersonResponsible = data.emailPersonResponsible;
    this.responsible = data.responsible;
    this.father = data.father;
    this.mother = data.mother;
    this.shifts = data.shifts;
    this.situation = data.situation;
  }

  fillOutFormData() {
    this.formData.id = this.idStudent;
    this.formData.name = this.name;
    this.formData.identity = this.identity;
    this.formData.cpf = this.cpf;
    this.formData.dateOfBirth = this.dateOfBirth;
    this.formData.nationality = this.nationality;
    this.formData.naturalness = this.naturalness;
    this.formData.sex = this.sex;
    this.formData.email = this.email;
    this.formData.cep = this.cep;
    this.formData.address = this.address;
    this.formData.phone = this.phone;
    this.formData.neighborhood = this.neighborhood;
    this.formData.city = this.city;
    this.formData.state = this.state;
    this.formData.country = this.country;
    this.formData.emailPersonResponsible = this.emailPersonResponsible;
    this.formData.responsible = this.responsible;
    this.formData.father = this.father;
    this.formData.mother = this.mother;
    this.formData.shifts = this.shifts[0];
  }

  sendForm() {
    this.fillOutFormData();
    this.studentService.updateStudent(this.formData).subscribe({
      next: (response: HttpResponse<any>) =>
        console.log(response),
      error: (error) => console.log('erro ao atualizar estudante!', error)
    })
  }
}