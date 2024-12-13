import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CheckRequestStatusComponent } from './check-request-status.component';

describe('CheckRequestStatusComponent', () => {
  let component: CheckRequestStatusComponent;
  let fixture: ComponentFixture<CheckRequestStatusComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CheckRequestStatusComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CheckRequestStatusComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
