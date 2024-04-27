import { Component, HostListener, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormStudentsDataService } from 'src/app/services/form-students-data.service';


interface Student {
  id: string,
  name: string,
  sex: string,
  createdAt: string,
}

@Component({
  selector: 'app-students-list',
  templateUrl: './students-list.component.html',
  styleUrls: ['./students-list.component.scss']
})
export class StudentsListComponent implements OnInit{
  students: Student[] = [];
  page: number = 0;
  size: number = 10;
  active: boolean = true;
  loading: boolean = false;
  lastScrollTop: number = 0;
  isThrottled = false;
  
  constructor(private studentService: FormStudentsDataService, private router: Router) {}

  ngOnInit(): void {
    this.loadMoreStudents();
  }

  loadMoreStudents() {
    if (this.loading) return;

    this.loading = true;
    this.studentService.findAllActiveStudents(this.active, this.page, this.size).subscribe(data => {
      let finishedDataStudents: Student[] = this.prepareData(data);
      this.students = this.students.concat(finishedDataStudents);  
      console.log("Carregando page" + this.page)
      this.page++;
      this.loading = false;
    });
  }

  prepareData(data: any) {
    let finishedStudents: Student[] = [];
    let dataStudents: Student[] = data.body.content;
    console.log(dataStudents)

    for (let student of dataStudents) {
      let sex = student.sex;
      let aux;

      if (sex == 'MASCULINE') {
        aux = 'MASCULINO';
        student.sex = aux;
      }

      if (sex == 'FEMININE') {
        aux = 'FEMININO';
        student.sex = aux;
      }

      let creationDateTime = new Date(student.createdAt);
      student.createdAt = this.formatDate(creationDateTime);

      finishedStudents.push(student);
    }
    return finishedStudents;
  }

  formatDate(date: Date): string {
    let day = date.getDate().toString().padStart(2, '0');
    let month = (date.getMonth() + 1).toString().padStart(2, '0');
    let year = date.getFullYear();
    return `${day}/${month}/${year}`;
  }

  @HostListener('window:scroll', ['$event'])
  onWindowScroll() {
    if (!this.isThrottled) {
      this.isThrottled = true;
      setTimeout(() => {
        const scrollTop = document.documentElement.scrollTop;
        if (scrollTop > this.lastScrollTop) {
          const buffer = 100;
          const scrolledToBottom = window.innerHeight + scrollTop >= document.body.offsetHeight - buffer;

          if (scrolledToBottom) {
            this.loadMoreStudents();
          }
        }
        this.lastScrollTop = scrollTop;
        this.isThrottled = false;
      }, 100);
    }
  }

  onScroll() {
    this.loadMoreStudents();
  }

  viewStudent(id: string) {
    if (id) {
      this.router.navigate(["/students/view/" + id]);
    } else {
      console.log("Erro ao encontrar esse id.");
    }
  }
}
