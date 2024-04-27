import { HttpClient, HttpResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { FormStudentsDataService } from 'src/app/services/form-students-data.service';

@Component({
  selector: 'app-students-disabled',
  templateUrl: './students-disabled.component.html',
  styleUrls: ['./students-disabled.component.scss']
})
export class StudentsDisabledComponent implements OnInit{
  studentId: string = '';
  form: FormGroup;

  constructor(
    private userService: FormStudentsDataService,
    private route: ActivatedRoute,
    private router: Router,
    private fb: FormBuilder) {
    this.form = this.fb.group({
      name: [''],
      createdAt: [''],
      course: [''],
    });
  }

  ngOnInit(): void {
    this.route.params.subscribe((params: Params) => {
      this.studentId = params['id'];
      console.log(this.studentId);

      if (this.studentId) {
        this.userService.findStudentById(this.studentId).subscribe({
          next: (response: HttpResponse<any>) => this.populateFields(response),
          error: (error) => console.log(error)
        });
      }
    })
  }

  clearFields(): void {
    this.form.patchValue({
      name: [''],
      createdAt: [''],
      course: [''],
    })
  }

  populateFields(response: any) {
    let creationDateTime = new Date(response.body.createdAt);

    if (response.status == 200) {
      this.form.patchValue({
        name: response.body.name,
        createdAt: this.formatDate(creationDateTime),
        course: ''
      });
    }
  }

  formatDate(date: Date): string {
    let day = date.getDate().toString().padStart(2, '0');
    let month = (date.getMonth() + 1).toString().padStart(2, '0');
    let year = date.getFullYear();
    return `${day}/${month}/${year}`;
  }

  disabledStudent(): void {
    this.userService.disabledStudent(this.studentId).subscribe({
      next: (response: HttpResponse<any>) => this.verifyResponse(response),
      error: (error) => console.log(error)
    })
  }

  verifyResponse(response: any) {
    if (response.status == 204) {
      alert("Aluno desabilitado.");
      this.router.navigate(["/students"]);
    }
  }
}
