import { Component, Input } from '@angular/core';
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
  @Input() destination: string = '';
  pendingButton: boolean = false;
  buttonFinishName: string = '';

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
    shifts: [],
    situation: '',
    imageProfile: ''
  }

  constructor(private formServiceData: FormStudentsDataService) {}

  ngOnInit(): void {    
    this.verifyDestination();
  }

  sendData(action: string) {
    if (action == 'register') {
      this.formData.situation = 'MATRICULADO';
      this.formServiceData.registerStudent(this.formData);
    } else if (action == 'pending') {
      this.formData.situation = 'PENDENTE';
      this.formServiceData.pendingStudent(this.formData);
    }
  }

  verifyDestination() {
    if (this.destination == 'register') {
      this.pendingButton = true;
      this.buttonFinishName = 'Finalizar cadastro';
    } else  {
      this.buttonFinishName = 'Atualizar cadastro';
    }
  }

  // formShifts = {
  //   matutino: '',
  //   vespertino: '',
  //   noturno: ''
  // }

  // onShiftChange(shiftValue: string, event: any): void {
  //   if(shiftValue in Shift) {
  //     const shift: Shift = Shift[shiftValue as keyof typeof Shift];
  //     if (shift && event.target.checked) {
  //         this.formShifts[shift] = shift.toUpperCase();
  //     } else {
  //         this.formShifts[shift] = '';
  //     }
  //   } 

  // }
}
