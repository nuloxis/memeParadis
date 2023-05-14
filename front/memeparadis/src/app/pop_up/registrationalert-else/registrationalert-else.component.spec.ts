import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegistrationalertElseComponent } from './registrationalert-else.component';

describe('RegistrationalertElseComponent', () => {
  let component: RegistrationalertElseComponent;
  let fixture: ComponentFixture<RegistrationalertElseComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RegistrationalertElseComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RegistrationalertElseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
