import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StudentsRegisterComponent } from './students-register.component';

describe('StudentsRegisterComponent', () => {
  let component: StudentsRegisterComponent;
  let fixture: ComponentFixture<StudentsRegisterComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [StudentsRegisterComponent]
    });
    fixture = TestBed.createComponent(StudentsRegisterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
