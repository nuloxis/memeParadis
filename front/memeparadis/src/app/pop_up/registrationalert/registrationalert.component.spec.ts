import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegistrationalertComponent } from './registrationalert.component';

describe('RegistrationalertComponent', () => {
  let component: RegistrationalertComponent;
  let fixture: ComponentFixture<RegistrationalertComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RegistrationalertComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RegistrationalertComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
