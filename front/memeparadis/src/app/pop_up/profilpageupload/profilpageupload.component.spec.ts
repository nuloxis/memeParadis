import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfilpageuploadComponent } from './profilpageupload.component';

describe('ProfilpageuploadComponent', () => {
  let component: ProfilpageuploadComponent;
  let fixture: ComponentFixture<ProfilpageuploadComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProfilpageuploadComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProfilpageuploadComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
