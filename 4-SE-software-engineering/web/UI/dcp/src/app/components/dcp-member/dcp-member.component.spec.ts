import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DcpMemberComponent } from './dcp-member.component';

describe('DcpMemberComponent', () => {
  let component: DcpMemberComponent;
  let fixture: ComponentFixture<DcpMemberComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DcpMemberComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DcpMemberComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
