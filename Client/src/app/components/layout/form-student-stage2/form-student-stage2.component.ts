import { HttpResponse } from '@angular/common/http';
import { Component, Input, OnInit } from '@angular/core';
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

  constructor(private formStudentService: FormStudentsDataService) {}

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
    type: '',
    city: '',
    phone: '',
    email: '',
    state: '',
    cep: '',
    dateOfBirth: ''
  }

  saveFamily() {
    alert(this.idStudent);
    console.log(this.formData);
    this.formStudentService.createFamily(this.formData).subscribe({
      next: (response: HttpResponse<any>) => {
        if (response.status == 201) {
          alert("Familiar salvo com sucesso!")
          this.atLeastOneRegisteredFamilyMember = true;
          this.isFirstFamily = false;
          this.nextStageAvaliable = true;
        }
      },
      error: (error) => console.log('Erro ao registrar familiar.', error)
      
    });
  }

  addNewFamily() {
    alert("adicinando novo familiar");
  }

  nextStage() {
    alert("etapa 3");
  }
}
