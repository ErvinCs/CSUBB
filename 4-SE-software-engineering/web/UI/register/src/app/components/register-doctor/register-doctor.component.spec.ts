import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RegisterDoctorComponent } from './register-doctor.component';

describe('RegisterDoctorComponent', () => {
  let component: RegisterDoctorComponent;
  let fixture: ComponentFixture<RegisterDoctorComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RegisterDoctorComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RegisterDoctorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
