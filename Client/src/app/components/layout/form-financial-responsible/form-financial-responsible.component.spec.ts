import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormFinancialResponsibleComponent } from './form-financial-responsible.component';

describe('FormFinancialResponsibleComponent', () => {
  let component: FormFinancialResponsibleComponent;
  let fixture: ComponentFixture<FormFinancialResponsibleComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [FormFinancialResponsibleComponent]
    });
    fixture = TestBed.createComponent(FormFinancialResponsibleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
