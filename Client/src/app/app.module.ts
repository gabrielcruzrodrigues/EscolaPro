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
import { SearchComponent } from './components/layout/search/search.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginPageComponent,
    MenuComponent,
    DashboardComponent,
    StudentsDashboardComponent,
    CardComponent,
    SearchComponent
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
