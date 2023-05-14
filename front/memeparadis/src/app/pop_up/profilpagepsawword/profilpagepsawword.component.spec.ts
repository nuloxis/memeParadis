import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfilpagepsawwordComponent } from './profilpagepsawword.component';

describe('ProfilpagepsawwordComponent', () => {
  let component: ProfilpagepsawwordComponent;
  let fixture: ComponentFixture<ProfilpagepsawwordComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProfilpagepsawwordComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProfilpagepsawwordComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
