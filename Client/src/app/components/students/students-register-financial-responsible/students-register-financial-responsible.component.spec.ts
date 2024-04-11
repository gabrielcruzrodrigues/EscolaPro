import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StudentsRegisterFinancialResponsibleComponent } from './students-register-financial-responsible.component';

describe('StudentsRegisterFinancialResponsibleComponent', () => {
  let component: StudentsRegisterFinancialResponsibleComponent;
  let fixture: ComponentFixture<StudentsRegisterFinancialResponsibleComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [StudentsRegisterFinancialResponsibleComponent]
    });
    fixture = TestBed.createComponent(StudentsRegisterFinancialResponsibleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
