import { HttpResponse } from '@angular/common/http';
import { Component, Input } from '@angular/core';
import { Router } from '@angular/router';
import { SearchStudentService } from 'src/app/services/search/search-student.service';

interface Student {
  id: number;
  name: string;
}

@Component({
  selector: 'app-search-student',
  templateUrl: './search-student.component.html',
  styleUrls: ['./search-student.component.scss']
})
export class SearchStudentComponent {
  @Input() label: string = '';
  @Input() destination: string = '';
  searchResults: Student[] = [];
  showResults: boolean = false;

  constructor(private searchService: SearchStudentService, private router: Router) { }

  onSearch(event: Event) {
    this.searchResults = [];
    const target = event.target as HTMLInputElement;
    const query = target.value;
    if (query.length > 0) {
      this.searchService.searchStudent(query).subscribe({
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

  goToUpdatePage(id: number) {
    this.router.navigate(['/students/update/', id]);
  }

  onMouseLeave() {
    this.showResults = false;
  }
}
