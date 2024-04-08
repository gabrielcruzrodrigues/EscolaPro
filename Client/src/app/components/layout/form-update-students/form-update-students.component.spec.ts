import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormUpdateStudentsComponent } from './form-update-students.component';

describe('FormUpdateStudentsComponent', () => {
  let component: FormUpdateStudentsComponent;
  let fixture: ComponentFixture<FormUpdateStudentsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [FormUpdateStudentsComponent]
    });
    fixture = TestBed.createComponent(FormUpdateStudentsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
