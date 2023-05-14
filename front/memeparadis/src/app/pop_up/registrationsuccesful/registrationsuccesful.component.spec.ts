import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegistrationsuccesfulComponent } from './registrationsuccesful.component';

describe('RegistrationsuccesfulComponent', () => {
  let component: RegistrationsuccesfulComponent;
  let fixture: ComponentFixture<RegistrationsuccesfulComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RegistrationsuccesfulComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RegistrationsuccesfulComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
