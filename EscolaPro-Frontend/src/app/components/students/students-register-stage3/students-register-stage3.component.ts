import { HttpResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormStudentsDataService } from 'src/app/services/form-students-data.service';
import { CofirmOutsideClick } from 'src/app/utils/ConfirmOutsideClick';

@Component({
  selector: 'app-students-register-stage3',
  templateUrl: './students-register-stage3.component.html',
  styleUrls: ['./students-register-stage3.component.scss']
})
export class StudentsRegisterStage3Component implements OnInit{
  idStudent: string = '';
  allergiesTextArea: string = '';

  constructor(
    private route: ActivatedRoute,
    private studentService: FormStudentsDataService, 
    private router: Router,
    private confirm: CofirmOutsideClick
    ) {}

  ngOnInit(): void {
    const param = this.route.snapshot.paramMap.get('id');
    this.idStudent = param !== null ? param : '';
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

  handleOutsideClick(event: Event): void {
    this.confirm.handleOutsideClick(event, 'student', this.idStudent);
  }

  finishingRegister() {
    if (this.allergiesTextArea != null) {
      this.formData.allergies = this.allergiesTextArea.split(',');
    } else {
      this.formData.allergies.push("sem alergia");
    }

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
