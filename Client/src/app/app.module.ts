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
import { FormStudentComponent } from './components/layout/form-student/form-student.component';
import { BackdoorComponent } from './components/layout/backdoor/backdoor.component';
import { StudentsUpdateComponent } from './components/students/students-update/students-update.component';
import { SearchStudentComponent } from './components/layout/search/search-student/search-student.component';
import { FormUpdateStudentsComponent } from './components/layout/form-update-students/form-update-students.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginPageComponent,
    MenuComponent,
    DashboardComponent,
    StudentsDashboardComponent,
    CardComponent,
    StudentsRegisterComponent,
    FormStudentComponent,
    BackdoorComponent,
    StudentsUpdateComponent,
    SearchStudentComponent,
    FormUpdateStudentsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
