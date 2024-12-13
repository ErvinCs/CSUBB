import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { WhyDonateBloodComponent } from './why-donate-blood.component';

describe('WhyDonateBloodComponent', () => {
  let component: WhyDonateBloodComponent;
  let fixture: ComponentFixture<WhyDonateBloodComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ WhyDonateBloodComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(WhyDonateBloodComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
