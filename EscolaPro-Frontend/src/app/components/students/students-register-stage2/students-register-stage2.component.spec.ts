import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StudentsRegisterStage2Component } from './students-register-stage2.component';

describe('StudentsRegisterStage2Component', () => {
  let component: StudentsRegisterStage2Component;
  let fixture: ComponentFixture<StudentsRegisterStage2Component>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [StudentsRegisterStage2Component]
    });
    fixture = TestBed.createComponent(StudentsRegisterStage2Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
