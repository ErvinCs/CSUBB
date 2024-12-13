import { TestBed, inject } from '@angular/core/testing';

import { DonorService } from './donor.service';

describe('DonorService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [DonorService]
    });
  });

  it('should be created', inject([DonorService], (service: DonorService) => {
    expect(service).toBeTruthy();
  }));
});
