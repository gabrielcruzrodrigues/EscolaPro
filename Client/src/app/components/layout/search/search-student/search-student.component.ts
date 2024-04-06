import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-search-student',
  templateUrl: './search-student.component.html',
  styleUrls: ['./search-student.component.scss']
})
export class SearchStudentComponent {
  @Input() label: string = '';
  @Input() destination: string = '';
  searchResults = ['Resultado 1', 'Resultado 2', 'Resultado 3'];
  showResults: boolean = false;

  onSearch(event: Event) {
    // Lógica da pesquisa aqui
    const target = event.target as HTMLInputElement;
    const query = target.value;
  }

  onBlur() {
    this.showResults = false;
  }
}
