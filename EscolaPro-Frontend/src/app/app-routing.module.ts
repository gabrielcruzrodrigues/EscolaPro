import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginPageComponent } from './login-page/login-page.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { authGuard } from './auth/auth.guard';
import { StudentsDashboardComponent } from './components/students/students-dashboard/students-dashboard.component';
import { StudentsRegisterComponent } from './components/students/students-register/students-register.component';
import { StudentsUpdateComponent } from './components/students/students-update/students-update.component';
import { StudentsRegisterStage2Component } from './components/students/students-register-stage2/students-register-stage2.component';
import { StudentsRegisterStage3Component } from './components/students/students-register-stage3/students-register-stage3.component';
import { StudentsRegisterFinancialResponsibleComponent } from './components/students/students-register-financial-responsible/students-register-financial-responsible.component';
import { StudentsViewComponent } from './components/students/students-view/students-view.component';
import { StudentsDisabledComponent } from './components/students/students-disabled/students-disabled.component';
import { StudentsListComponent } from './components/students/students-list/students-list.component';
import { StudentsPendingListComponent } from './components/students/students-pending-list/students-pending-list.component';

const routes: Routes = [
  {path: "", component: LoginPageComponent},
  {path: "dashboard", component: DashboardComponent, canActivate: [authGuard]},
  {path: "students", component: StudentsDashboardComponent, canActivate: [authGuard]},
  {path: "students/register", component: StudentsRegisterComponent, canActivate: [authGuard]},
  {path: "students/view", component: StudentsViewComponent, canActivate: [authGuard]}, 
  {path: "students/view/:id", component: StudentsViewComponent, canActivate: [authGuard]}, 
  {path: "students/update", component: StudentsUpdateComponent, canActivate: [authGuard]},
  {path: "students/update/:id", component: StudentsUpdateComponent, canActivate: [authGuard]},
  {path: "students/disabled", component: StudentsDisabledComponent, canActivate: [authGuard]},
  {path: "students/disabled/:id", component: StudentsDisabledComponent, canActivate: [authGuard]},
  {path: "students/list", component: StudentsListComponent, canActivate: [authGuard]},
  {path: "students/list/pending", component: StudentsPendingListComponent, canActivate: [authGuard]},
  {path: "students/register/stage2/:id", component: StudentsRegisterStage2Component, canActivate: [authGuard]},
  {path: "students/register/stage3/:id", component: StudentsRegisterStage3Component, canActivate: [authGuard]},
  {path: "students/register/financialresponsible/:id", component: StudentsRegisterFinancialResponsibleComponent, canActivate: [authGuard]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
