import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AlunosDashboardComponent } from './alunos-dashboard.component';

describe('AlunosDashboardComponent', () => {
  let component: AlunosDashboardComponent;
  let fixture: ComponentFixture<AlunosDashboardComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AlunosDashboardComponent]
    });
    fixture = TestBed.createComponent(AlunosDashboardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
