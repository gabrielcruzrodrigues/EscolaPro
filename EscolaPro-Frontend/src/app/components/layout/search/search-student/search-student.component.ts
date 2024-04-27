import { HttpResponse } from '@angular/common/http';
import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormStudentsDataService } from 'src/app/services/form-students-data.service';

interface Student {
  id: string;
  name: string;
}

@Component({
  selector: 'app-search-student',
  templateUrl: './search-student.component.html',
  styleUrls: ['./search-student.component.scss']
})
export class SearchStudentComponent implements OnInit{
  @Input() label: string = '';
  @Input() destination: string = '';
  searchResults: Student[] = [];
  showResults: boolean = false;

  constructor(
    private router: Router,
    private studentService: FormStudentsDataService) { }

  ngOnInit(): void {
    this.searchResults = [];
  }

  onSearch(event: Event) {
    this.searchResults = [];
    const target = event.target as HTMLInputElement;
    const query = target.value;
 
    if (query.length > 0) {
      this.studentService.searchStudentByName(query).subscribe({
        next: (response: HttpResponse<any>) => {
          if (response.body) {
            const responseData = response.body;

            if (Array.isArray(responseData)) {
              this.searchResults = responseData.slice(0, 10).map(student => ({
                id: student.id,
                name: student.name
              }));
              
            } else {
              console.log("Os dados no corpo da resposta não são um array.");
            }

          } else {
            console.log("A resposta não possui um corpo (body).");
          }

        },
        error: (error) => {
          
        }
      });
    }
    
  }

  onBlur() {
    this.showResults = false;
  }

  redirectResult(id: any) {
    switch(this.destination) {
      case 'update':
        this.router.navigate(['/students/update', id]);
        break;
      case 'disabled':
        this.router.navigate(['/students/disabled/', id]);
        break;
      case 'view':
        this.router.navigate(['/students/view/', id]);
        break;
    }
    this.searchResults = [];
  }

  onMouseLeave() {
    this.showResults = false;
  }
}
