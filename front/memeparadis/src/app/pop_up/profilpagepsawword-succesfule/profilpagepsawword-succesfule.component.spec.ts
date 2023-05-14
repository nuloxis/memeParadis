import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfilpagepsawwordSuccesfuleComponent } from './profilpagepsawword-succesfule.component';

describe('ProfilpagepsawwordSuccesfuleComponent', () => {
  let component: ProfilpagepsawwordSuccesfuleComponent;
  let fixture: ComponentFixture<ProfilpagepsawwordSuccesfuleComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProfilpagepsawwordSuccesfuleComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProfilpagepsawwordSuccesfuleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
