import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-students-register-financial-responsible',
  templateUrl: './students-register-financial-responsible.component.html',
  styleUrls: ['./students-register-financial-responsible.component.scss']
})
export class StudentsRegisterFinancialResponsibleComponent implements OnInit{
  idStudent: string = '';

  constructor(private route: ActivatedRoute) {}
   ngOnInit(): void {
      const param = this.route.snapshot.paramMap.get('id');
      this.idStudent = param !== null ? param : '';
   }
}
