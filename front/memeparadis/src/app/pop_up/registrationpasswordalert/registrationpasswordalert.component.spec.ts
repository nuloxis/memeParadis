import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegistrationpasswordalertComponent } from './registrationpasswordalert.component';

describe('RegistrationpasswordalertComponent', () => {
  let component: RegistrationpasswordalertComponent;
  let fixture: ComponentFixture<RegistrationpasswordalertComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RegistrationpasswordalertComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RegistrationpasswordalertComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
