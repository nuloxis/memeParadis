import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfilpageuploadsucessfulComponent } from './profilpageuploadsucessful.component';

describe('ProfilpageuploadsucessfulComponent', () => {
  let component: ProfilpageuploadsucessfulComponent;
  let fixture: ComponentFixture<ProfilpageuploadsucessfulComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProfilpageuploadsucessfulComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProfilpageuploadsucessfulComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
