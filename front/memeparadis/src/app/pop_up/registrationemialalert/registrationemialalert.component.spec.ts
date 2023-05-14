import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegistrationemialalertComponent } from './registrationemialalert.component';

describe('RegistrationemialalertComponent', () => {
  let component: RegistrationemialalertComponent;
  let fixture: ComponentFixture<RegistrationemialalertComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RegistrationemialalertComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RegistrationemialalertComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
