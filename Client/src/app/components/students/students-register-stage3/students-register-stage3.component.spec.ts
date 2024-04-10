import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StudentsRegisterStage3Component } from './students-register-stage3.component';

describe('StudentsRegisterStage3Component', () => {
  let component: StudentsRegisterStage3Component;
  let fixture: ComponentFixture<StudentsRegisterStage3Component>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [StudentsRegisterStage3Component]
    });
    fixture = TestBed.createComponent(StudentsRegisterStage3Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
