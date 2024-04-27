import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StudentsDashboardComponent } from './students-dashboard.component';

describe('StudentsDashboardComponent', () => {
  let component: StudentsDashboardComponent;
  let fixture: ComponentFixture<StudentsDashboardComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [StudentsDashboardComponent]
    });
    fixture = TestBed.createComponent(StudentsDashboardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
