import { TestBed, inject } from '@angular/core/testing';

import { DcpMemberService } from './dcp-member.service';

describe('DcpMemberService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [DcpMemberService]
    });
  });

  it('should be created', inject([DcpMemberService], (service: DcpMemberService) => {
    expect(service).toBeTruthy();
  }));
});
