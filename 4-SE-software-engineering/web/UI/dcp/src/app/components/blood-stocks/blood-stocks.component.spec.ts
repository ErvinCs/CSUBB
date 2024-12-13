import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BloodStocksComponent } from './blood-stocks.component';

describe('BloodStocksComponent', () => {
  let component: BloodStocksComponent;
  let fixture: ComponentFixture<BloodStocksComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BloodStocksComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BloodStocksComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
