import { TestBed } from '@angular/core/testing';

import { SearchStudentService } from './search-student.service';

describe('SearchStudentService', () => {
  let service: SearchStudentService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SearchStudentService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
