import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-form-student',
  templateUrl: './form-student.component.html',
  styleUrls: ['./form-student.component.scss']
})
export class FormStudentComponent {
  @Input() destination: string = '';
}
