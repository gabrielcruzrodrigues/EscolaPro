import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-students-update',
  templateUrl: './students-update.component.html',
  styleUrls: ['./students-update.component.scss']
})
export class StudentsUpdateComponent implements OnInit{
  idStudent: number = 0;

  constructor(private route: ActivatedRoute) { }

  ngOnInit() {
    const param = this.route.snapshot.paramMap.get('id');
    this.idStudent = param !== null ? +param : 0;
    console.log(this.idStudent);
  }
}
