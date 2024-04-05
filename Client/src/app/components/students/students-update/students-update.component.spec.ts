import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StudentsUpdateComponent } from './students-update.component';

describe('StudentsUpdateComponent', () => {
  let component: StudentsUpdateComponent;
  let fixture: ComponentFixture<StudentsUpdateComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [StudentsUpdateComponent]
    });
    fixture = TestBed.createComponent(StudentsUpdateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
