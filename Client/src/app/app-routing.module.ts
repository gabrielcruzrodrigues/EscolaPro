import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginPageComponent } from './login-page/login-page.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { authGuard } from './auth/auth.guard';
import { StudentsDashboardComponent } from './components/students/students-dashboard/students-dashboard.component';
import { StudentsRegisterComponent } from './components/students/students-register/students-register.component';

const routes: Routes = [
  {path: "", component: LoginPageComponent},
  {path: "dashboard", component: DashboardComponent, canActivate: [authGuard]},
  {path: "students", component: StudentsDashboardComponent, canActivate: [authGuard]},
  {path: "students/register", component: StudentsRegisterComponent, canActivate: [authGuard]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
