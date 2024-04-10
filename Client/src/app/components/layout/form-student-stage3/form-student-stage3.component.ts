import { HttpResponse } from '@angular/common/http';
import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormStudentsDataService } from 'src/app/services/form-students-data.service';

@Component({
  selector: 'app-form-student-stage3',
  templateUrl: './form-student-stage3.component.html',
  styleUrls: ['./form-student-stage3.component.scss']
})
export class FormStudentStage3Component implements OnInit{
  @Input() idStudent: string = '';
  allergiesTextArea: string = '';

  constructor(private studentService: FormStudentsDataService, private router: Router) {}

  ngOnInit(): void {
    this.formData.studentId = this.idStudent;
  }

  formData = {
    studentId: '',
    allergies: [] as string[],
    healthInsurance: '',
    bloodGroup: '',
    medicalClinic: '',
    quantityBrothers: '',
    toGoOutAuthorization: ''
  }

  finishingRegister() {
    this.formData.allergies = this.allergiesTextArea.split(',');

    this.studentService.createFixedhealth(this.formData).subscribe({
      next: (response: HttpResponse<any>) => {
        alert("Fixa de saude cadastrada.");
        if (response.status == 201) {
          this.router.navigate(["/students"])
        }
      },
      error: (error) => 
        console.log("erro ao salvar ficha de saúde. verifique os campos." + error.message)
    })
  }
}
