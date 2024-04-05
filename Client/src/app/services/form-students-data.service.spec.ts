import { TestBed } from '@angular/core/testing';

import { FormStudentsDataService } from './form-students-data.service';

describe('FormStudentsDataService', () => {
  let service: FormStudentsDataService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FormStudentsDataService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
