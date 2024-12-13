import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { IdleDonorsComponent } from './idle-donors.component';

describe('IdleDonorsComponent', () => {
  let component: IdleDonorsComponent;
  let fixture: ComponentFixture<IdleDonorsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ IdleDonorsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(IdleDonorsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
