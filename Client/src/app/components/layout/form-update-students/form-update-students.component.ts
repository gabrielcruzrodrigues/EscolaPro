import { HttpResponse } from '@angular/common/http';
import { Component, Input } from '@angular/core';
import { Router } from '@angular/router';

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
export class FormUpdateStudentsComponent {
  idStudent: number = 0;

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


  constructor(private router: Router) {}

  sendData() {

  }

}
