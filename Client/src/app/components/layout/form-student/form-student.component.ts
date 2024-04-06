import { HttpResponse } from '@angular/common/http';
import { Component, Input } from '@angular/core';
import { Router } from '@angular/router';
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
  @Input() buttonUpdate: string = 'false'; 
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
    shifts: '',
    situation: ''
    // imageProfile: ''
  }

  constructor(private formServiceData: FormStudentsDataService,  private router: Router) {}

  ngOnInit(): void {    
    this.verifyDestination();
  }

  sendData(action: string) {

    if (action == 'register' && this.buttonUpdate == 'true') {  //update
      this.formServiceData.updateStudent(this.formData);
      

    } else if (action == 'pending') {
      this.formData.situation = 'PENDENTE';
      this.formServiceData.pendingStudent(this.formData).subscribe({
        next: (response: HttpResponse<any>) => this.verifyResponse(response.status)});

    } else if (action == 'register') {
      this.formData.situation = 'MATRICULADO';
      this.formServiceData.registerStudent(this.formData).subscribe({
        next: (response: HttpResponse<any>) => this.verifyResponse(response.status),
        error: (err) => console.log('Erro ao registrar estudante', err)
      });
    }
  }

  verifyResponse(data: any) {
    // console.log('response: ', data)
    if (data == 201) {
      alert("Usuário criado com sucesso!");
      this.router.navigate(["/students"]);
    } else {
      alert("Erro ao tentar enviar formulário.");
    }
  }

  verifyDestination() {
    if (this.destination == 'register') {
      this.pendingButton = true;
      this.buttonFinishName = 'Finalizar cadastro';
    } else {
      this.buttonFinishName = 'Atualizar cadastro';
    }
  }
}
