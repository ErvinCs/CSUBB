import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BloodRequestComponent } from './blood-request.component';

describe('BloodRequestComponent', () => {
  let component: BloodRequestComponent;
  let fixture: ComponentFixture<BloodRequestComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BloodRequestComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BloodRequestComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
