import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormStudentStage2Component } from './form-student-stage2.component';

describe('FormStudentStage2Component', () => {
  let component: FormStudentStage2Component;
  let fixture: ComponentFixture<FormStudentStage2Component>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [FormStudentStage2Component]
    });
    fixture = TestBed.createComponent(FormStudentStage2Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
