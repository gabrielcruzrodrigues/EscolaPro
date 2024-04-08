import { HttpResponse } from '@angular/common/http';
import { Component, Input, OnInit } from '@angular/core';
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


  constructor(private searchService: SearchStudentService) {}

  ngOnInit(): void {
      this.sendData(this.idStudent);
  }


  sendData(data: any) {
    const id = data;
    this.searchService.findStudentById(id).subscribe({
      next: (response: HttpResponse<any>) => {
        console.log(response);
      },
      error: (error) => {
        console.log(error);
      }
    })
  }

}