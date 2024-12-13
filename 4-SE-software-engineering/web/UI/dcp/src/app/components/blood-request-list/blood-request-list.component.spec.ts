import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BloodRequestListComponent } from './blood-request-list.component';

describe('BloodRequestListComponent', () => {
  let component: BloodRequestListComponent;
  let fixture: ComponentFixture<BloodRequestListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BloodRequestListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BloodRequestListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
