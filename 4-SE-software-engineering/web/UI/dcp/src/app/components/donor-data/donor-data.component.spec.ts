import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DonorDataComponent } from './donor-data.component';

describe('DonorDataComponent', () => {
  let component: DonorDataComponent;
  let fixture: ComponentFixture<DonorDataComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DonorDataComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DonorDataComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
