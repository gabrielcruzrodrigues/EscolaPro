import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormStudentStage3Component } from './form-student-stage3.component';

describe('FormStudentStage3Component', () => {
  let component: FormStudentStage3Component;
  let fixture: ComponentFixture<FormStudentStage3Component>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [FormStudentStage3Component]
    });
    fixture = TestBed.createComponent(FormStudentStage3Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
