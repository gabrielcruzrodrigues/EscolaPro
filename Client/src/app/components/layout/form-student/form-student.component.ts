import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-form-student',
  templateUrl: './form-student.component.html',
  styleUrls: ['./form-student.component.scss']
})
export class FormStudentComponent {
  @Input() destination: string = '';
  pendingButton: boolean = false;
  buttonFinishName: string = '';

  constructor() {}

  ngOnInit(): void {    
    this.verifyDestination();
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
