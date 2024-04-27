import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StudentsDisabledComponent } from './students-disabled.component';

describe('StudentsDisabledComponent', () => {
  let component: StudentsDisabledComponent;
  let fixture: ComponentFixture<StudentsDisabledComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [StudentsDisabledComponent]
    });
    fixture = TestBed.createComponent(StudentsDisabledComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
