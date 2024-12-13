import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NextDonationComponent } from './next-donation.component';

describe('NextDonationComponent', () => {
  let component: NextDonationComponent;
  let fixture: ComponentFixture<NextDonationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NextDonationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NextDonationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
