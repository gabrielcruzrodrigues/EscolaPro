import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-students-register-stage2',
  templateUrl: './students-register-stage2.component.html',
  styleUrls: ['./students-register-stage2.component.scss']
})
export class StudentsRegisterStage2Component implements OnInit{
  idStudent: string = '';

  constructor(private route: ActivatedRoute) {}
   ngOnInit(): void {
      const param = this.route.snapshot.paramMap.get('id');
      this.idStudent = param !== null ? param : '';
   }
}
