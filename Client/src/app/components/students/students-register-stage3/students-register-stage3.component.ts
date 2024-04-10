import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-students-register-stage3',
  templateUrl: './students-register-stage3.component.html',
  styleUrls: ['./students-register-stage3.component.scss']
})
export class StudentsRegisterStage3Component implements OnInit{
  idStudent: string = '';

  constructor(private route: ActivatedRoute) {}

  ngOnInit(): void {
    const param = this.route.snapshot.paramMap.get('id');
    this.idStudent = param !== null ? param : '';
  }
}
