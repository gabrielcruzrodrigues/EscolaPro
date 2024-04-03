import { Routes } from '@angular/router';
import { LoginPageComponent } from './components/login-page/login-page.component';
import { DashboardGeralComponent } from './components/dashboard-geral/dashboard-geral.component';

export const routes: Routes = [
     {
          path: "",
          component: LoginPageComponent
     },
     {
          path: "dashboard",
          component: DashboardGeralComponent
     }
];
