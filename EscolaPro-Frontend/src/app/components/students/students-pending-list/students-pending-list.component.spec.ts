import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StudentsPendingListComponent } from './students-pending-list.component';

describe('StudentsPendingListComponent', () => {
  let component: StudentsPendingListComponent;
  let fixture: ComponentFixture<StudentsPendingListComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [StudentsPendingListComponent]
    });
    fixture = TestBed.createComponent(StudentsPendingListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
