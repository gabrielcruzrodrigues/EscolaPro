import { Component } from '@angular/core';
import { MenuComponent } from '../menu/menu.component';

@Component({
  selector: 'app-dashboard-geral',
  standalone: true,
  imports: [MenuComponent],
  templateUrl: './dashboard-geral.component.html',
  styleUrl: './dashboard-geral.component.scss'
})
export class DashboardGeralComponent {

}
