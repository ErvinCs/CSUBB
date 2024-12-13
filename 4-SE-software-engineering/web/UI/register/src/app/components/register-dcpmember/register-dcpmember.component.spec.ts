import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RegisterDcpmemberComponent } from './register-dcpmember.component';

describe('RegisterDcpmemberComponent', () => {
  let component: RegisterDcpmemberComponent;
  let fixture: ComponentFixture<RegisterDcpmemberComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RegisterDcpmemberComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RegisterDcpmemberComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
