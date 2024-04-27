import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginPageComponent } from './login-page/login-page.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { MenuComponent } from './components/layout/menu/menu.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { StudentsDashboardComponent } from './components/students/students-dashboard/students-dashboard.component';
import { CardComponent } from './components/layout/card/card.component';
import { StudentsRegisterComponent } from './components/students/students-register/students-register.component';
import { BackdoorComponent } from './components/layout/backdoor/backdoor.component';
import { StudentsUpdateComponent } from './components/students/students-update/students-update.component';
import { SearchStudentComponent } from './components/layout/search/search-student/search-student.component';
import { StudentsRegisterStage2Component } from './components/students/students-register-stage2/students-register-stage2.component';
import { StudentsRegisterStage3Component } from './components/students/students-register-stage3/students-register-stage3.component';
import { StudentsRegisterFinancialResponsibleComponent } from './components/students/students-register-financial-responsible/students-register-financial-responsible.component';
import { StudentsViewComponent } from './components/students/students-view/students-view.component';
import { StudentsDisabledComponent } from './components/students/students-disabled/students-disabled.component';
import { StudentsListComponent } from './components/students/students-list/students-list.component';
import { StudentsPendingListComponent } from './components/students/students-pending-list/students-pending-list.component';
import { CofirmOutsideClick } from './utils/ConfirmOutsideClick';

@NgModule({
  declarations: [
    AppComponent,
    LoginPageComponent,
    MenuComponent,
    DashboardComponent,
    StudentsDashboardComponent,
    CardComponent,
    StudentsRegisterComponent,
    BackdoorComponent,
    StudentsUpdateComponent,
    SearchStudentComponent,
    StudentsRegisterStage2Component,
    StudentsRegisterStage3Component,
    StudentsRegisterFinancialResponsibleComponent,
    StudentsViewComponent,
    StudentsDisabledComponent,
    StudentsListComponent,
    StudentsPendingListComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [
    CofirmOutsideClick
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
