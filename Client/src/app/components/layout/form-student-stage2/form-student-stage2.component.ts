import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-form-student-stage2',
  templateUrl: './form-student-stage2.component.html',
  styleUrls: ['./form-student-stage2.component.scss']
})
export class FormStudentStage2Component {
  @Input() idStudent: string = '';

  formData = {
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

  nextStage() {
    alert(this.idStudent);
  }

  addFamily() {

  }
}
