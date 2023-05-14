import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegistrationbirthalertComponent } from './registrationbirthalert.component';

describe('RegistrationbirthalertComponent', () => {
  let component: RegistrationbirthalertComponent;
  let fixture: ComponentFixture<RegistrationbirthalertComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RegistrationbirthalertComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RegistrationbirthalertComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
