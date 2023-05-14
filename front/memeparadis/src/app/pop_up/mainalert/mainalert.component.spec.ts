import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MainalertComponent } from './mainalert.component';

describe('MainalertComponent', () => {
  let component: MainalertComponent;
  let fixture: ComponentFixture<MainalertComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MainalertComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MainalertComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
